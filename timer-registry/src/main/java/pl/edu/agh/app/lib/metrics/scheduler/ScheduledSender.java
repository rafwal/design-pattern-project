package pl.edu.agh.app.lib.metrics.scheduler;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
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
    private ScheduledExecutorService executorService;

    private Multimap<String, Timer> timers = Multimaps.synchronizedMultimap(HashMultimap.create());


    public void startWithIntervalInSeconds(long interval) {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(this::sendData,
                interval, interval, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    /*
        Sends data and clears timer registry
     */
    private void sendData() {
        timerRegistry.assignTimersAndReset(timers);
        String json = JsonUtils.toJson(timers.asMap());

        synchronized(this) {
            try {
                requestorConnector.doSend(json);
                timers.clear();
            } catch (IOException e) {
                System.err.println("COULD NOT CONNECT");
            }
        }

    }


    public ScheduledSender(RequestorConnector requestorConnector) {
        this.timerRegistry = TimerRegistry.getInstance();
        this.requestorConnector = requestorConnector;
    }
}
