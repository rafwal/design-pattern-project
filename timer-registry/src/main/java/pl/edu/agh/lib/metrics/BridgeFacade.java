package pl.edu.agh.lib.metrics;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class BridgeFacade {
    private InetAddress address;
    private int port;
    private String appName;

    public BridgeFacade(InetAddress address, String appName, int port) {
        this.address = address;
        this.appName = appName;
        this.port = port;
    }

    public void doSend(String json) throws IOException {
        try (   Socket socket = new Socket(address, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
        {
            out.println("SEND TIMERS");
            out.println(appName);
            out.println(json);
            out.println("END");
        }
    }
}
