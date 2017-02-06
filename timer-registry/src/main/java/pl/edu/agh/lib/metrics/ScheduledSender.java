package pl.edu.agh.lib.metrics;


import com.google.common.collect.Multimap;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledSender {

    private final TimerRegistry timerRegistry;
    private final BridgeFacade bridgeFacade;
    private final JsonUtils jsonUtils;

    public void startSendingWithInterval(long interval) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(this::sendData,
                interval, interval, TimeUnit.SECONDS);
    }

    private void sendData() {

        Multimap<String, Timer> timers = timerRegistry.getTimersAndReset();
        String json = jsonUtils.toJson(timers.asMap());

        try {
            bridgeFacade.doSend(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ScheduledSender(TimerRegistry timerRegistry, BridgeFacade bridgeFacade) {
        this.timerRegistry = timerRegistry;
        this.jsonUtils = new JsonUtils();
        this.bridgeFacade = bridgeFacade;
    }
}
