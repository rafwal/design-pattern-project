package pl.edu.agh.lib.metrics.scheduler;


import com.google.common.collect.Multimap;
import pl.edu.agh.lib.metrics.timers.Timer;
import pl.edu.agh.lib.metrics.timers.TimerRegistry;
import pl.edu.agh.lib.metrics.facade.RequestorFacade;
import pl.edu.agh.lib.metrics.util.JsonUtils;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledSender {

    private final TimerRegistry timerRegistry;
    private final RequestorFacade requestorFacade;
    private final JsonUtils jsonUtils;

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
        String json = jsonUtils.toJson(timers.asMap());

        try {
            requestorFacade.doSend(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ScheduledSender(TimerRegistry timerRegistry, RequestorFacade requestorFacade) {
        this.timerRegistry = timerRegistry;
        this.jsonUtils = new JsonUtils();
        this.requestorFacade = requestorFacade;
    }
}
