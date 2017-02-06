import pl.edu.agh.lib.metrics.facade.RequestorFacade;
import pl.edu.agh.lib.metrics.scheduler.ScheduledSender;
import pl.edu.agh.lib.metrics.timers.Timer;
import pl.edu.agh.lib.metrics.timers.TimerRegistry;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {

        TimerRegistry timerRegistry = new TimerRegistry();
        RequestorFacade bridge = new RequestorFacade(InetAddress.getLocalHost(), "app", 1234);
        ScheduledSender scheduledSender = new ScheduledSender(timerRegistry, bridge);
        scheduledSender.startSendingWithIntervalInSeconds(21);

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
