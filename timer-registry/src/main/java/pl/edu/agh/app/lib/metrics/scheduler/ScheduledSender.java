package pl.edu.agh.app.lib.metrics.scheduler;


import com.google.common.collect.Multimap;
import pl.edu.agh.app.lib.metrics.RequestorConnector;
import pl.edu.agh.app.lib.metrics.timers.Timer;
import pl.edu.agh.app.lib.metrics.util.JsonUtils;
import pl.edu.agh.app.lib.metrics.timers.TimerRegistry;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//scheduled-task pattern
public class ScheduledSender {

    private final TimerRegistry timerRegistry;
    private final RequestorConnector requestorConnector;

    /*
        handles sending data in interval given inSECONDS
     */
    public void startSendingWithIntervalInSeconds(long interval) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(this::sendData,
                interval, interval, TimeUnit.SECONDS);
    }

    /*
        Sends data and clears timer registry
     */
    private void sendData() {

        Multimap<String, Timer> timers = timerRegistry.getTimersAndReset();
        String json = JsonUtils.toJson(timers.asMap());

        try {
            requestorConnector.doSend(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ScheduledSender(RequestorConnector requestorConnector) {
        this.timerRegistry = TimerRegistry.getInstance();
        this.requestorConnector = requestorConnector;
    }
}
