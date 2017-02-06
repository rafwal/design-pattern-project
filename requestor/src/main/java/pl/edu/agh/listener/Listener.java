package pl.edu.agh.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.app.model.entity.execution.TestExecution;
import pl.edu.agh.app.model.entity.execution.Timer;
import pl.edu.agh.app.repository.TimerRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Listener {

    private TimersGatherer timersGatherer;

    @Autowired
    private TimerRepository timerRepository;


    private final int port = 1234;

    ServerSocket serverSocket;

    public void doStartListening() {
        timersGatherer = new TimersGatherer();
        openServerSocket();
        System.out.println("Socket open at port  " + port);

        new Thread(() -> {
            try {
                while (true) {
                        Socket socket = serverSocket.accept();
                        handleSocket(socket);
                }
            } catch (SocketException se) {
                System.err.println("Socket closed");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).start();
    }

    private void handleSocket(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String line;
        if (!"SEND TIMERS".equals(in.readLine())) {
            System.err.println("Request ejected on step one");
        }

        if (!"SEND TIMERS".equals(in.readLine())) {
            System.err.println("Request ejected on step one");
        }

        StringBuilder sb = new StringBuilder();
        while(!(line = in.readLine()).equals("END")) {
            sb.append(line);
        }
        socket.close();
        timersGatherer.addTimers(sb.toString());
    }

    private void openServerSocket() {

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open socket port " + port, e);
        }
    }

    public void stopListening() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close the socket", e);
        }
        serverSocket = null;
    }

    public void processTimers(TestExecution afterSave) {
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
                    });
                    timerRepository.save(timers);
                }
        );
        System.err.println("Timers processed");
    }
}
