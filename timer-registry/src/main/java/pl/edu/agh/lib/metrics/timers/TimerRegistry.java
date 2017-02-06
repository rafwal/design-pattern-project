package pl.edu.agh.lib.metrics.timers;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import pl.edu.agh.lib.metrics.timers.Timer;

/*
    holds all timers in memory
 */
public class TimerRegistry {

    private Multimap<String, Timer> timers;

    public Multimap<String, Timer> getTimersAndReset() {
        Multimap<String,Timer> oldTimers = timers;
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
        return oldTimers;
    }

    public TimerRegistry() {
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
    }

    public Timer timer(String timerName) {
        Timer timer = new Timer();
        timers.put(timerName, timer);
        timer.start();
        return timer;
    }

}
