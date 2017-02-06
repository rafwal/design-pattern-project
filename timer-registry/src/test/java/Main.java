import pl.edu.agh.lib.metrics.BridgeFacade;
import pl.edu.agh.lib.metrics.ScheduledSender;
import pl.edu.agh.lib.metrics.Timer;
import pl.edu.agh.lib.metrics.TimerRegistry;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {

        TimerRegistry timerRegistry = new TimerRegistry("app");
        BridgeFacade bridge = new BridgeFacade(InetAddress.getLocalHost(), "app", 1234);
        ScheduledSender scheduledSender = new ScheduledSender(timerRegistry, bridge);
        scheduledSender.startSendingWithInterval(21);

        while(true) {
            IntStream.range(0, 10).forEach(
                    i -> {
                        try (Timer timer = timerRegistry.timer("timer1")) {
                            System.err.println(i);
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
            System.err.println("-------");
            Thread.sleep(20000);
        }

    }
}
