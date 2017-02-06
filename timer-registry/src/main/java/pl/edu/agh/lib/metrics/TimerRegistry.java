package pl.edu.agh.lib.metrics;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;


public class TimerRegistry {

    private String appName;
    private Multimap<String, Timer> timers;


    public Multimap<String, Timer> getTimersAndReset() {
        Multimap<String,Timer> oldTimers = timers;
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
        return oldTimers;
    }

    public TimerRegistry(String appName) {
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
        this.appName = appName;
    }

    public Timer timer(String timerName) {
        Timer timer = new Timer();
        timers.put(timerName, timer);
        timer.start();
        return timer;
    }

    public String getAppName() {
        return appName;
    }
}
