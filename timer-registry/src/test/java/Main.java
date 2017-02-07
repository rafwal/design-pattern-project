import pl.edu.agh.app.lib.metrics.RequestorConnector;
import pl.edu.agh.app.lib.metrics.scheduler.ScheduledSender;
import pl.edu.agh.app.lib.metrics.timers.Timer;
import pl.edu.agh.app.lib.metrics.timers.TimerRegistry;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {

        RequestorConnector bridge = new RequestorConnector(InetAddress.getLocalHost(), "app", 1234);
        ScheduledSender scheduledSender = new ScheduledSender(bridge);
        scheduledSender.startWithIntervalInSeconds(21);

        while(true) {
            IntStream.range(0, 10).forEach(
                    i -> {
                        try (Timer timer = TimerRegistry.getInstance().timer("timer1")) {
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
