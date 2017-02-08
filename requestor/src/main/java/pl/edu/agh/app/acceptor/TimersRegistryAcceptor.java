package pl.edu.agh.app.acceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;
import pl.edu.agh.app.requestor.repository.TimerRepository;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimersRegistryAcceptor {


    private TimersGatherer timersGatherer;

    @Autowired
    private TimerRepository timerRepository;

    //todo properties
    private final int port = 1234;


    ServerSocket serverSocket;

    //just one gatherer can work in the same time... change it?
    public void doStartListening(String appName) {
        timersGatherer = new TimersGatherer();
        openServerSocket();
        System.out.println("Socket open at port  " + port);

        new Thread(() -> {
            try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    handleSocket(socket, appName);
                }
            } catch (SocketException se) {
                System.err.println("Socket closed");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).start();
    }


    public void stopListening() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close the socket", e);
        }
        serverSocket = null;
    }

    public void stopListeningIn(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            stopListening();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void processTimers(TestExecution afterSave, String appName) {
        if (timersGatherer.getTimers().isEmpty()) {
            System.err.println("No timers registered");
            return;
        }
        timersGatherer.getTimers().keySet().forEach(
                k -> {
                    List<Timer> timers =
                            timersGatherer.getTimers().get(k).stream()
                                    .map(TimerDTO::toEntity)
                                    .collect(Collectors.toList());
                    timers.forEach(t -> {
                        t.setName(k);
                        t.setTestExecution(afterSave);
                        t.setAppName(appName);
                    });
                    timerRepository.save(timers);
                }
        );
        System.err.println("Timers processed");
    }

    private void handleSocket(Socket socket, String appName) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String line;
        if (!"SEND TIMERS".equals(in.readLine())) {
            System.err.println("Request ejected on step one");
            return;
        }

        if (!appName.equals(in.readLine())) {
            System.err.println("Request ejected on step two - wrong app name");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!(line = in.readLine()).equals("END")) {
            sb.append(line);
        }
        socket.close();
        timersGatherer.addTimers(sb.toString(), appName);
    }

    private void openServerSocket() {

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open socket port " + port, e);
        }
    }
}
