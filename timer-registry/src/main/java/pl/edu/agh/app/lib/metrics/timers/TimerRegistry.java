package pl.edu.agh.app.lib.metrics.timers;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/*
    holds all timers in memory
 */
//design-pattern singleton
public class TimerRegistry {

    private Multimap<String, Timer> timers;

    public Multimap<String, Timer> getTimersAndReset() {
        Multimap<String,Timer> oldTimers = timers;
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
        return oldTimers;
    }


    public Timer timer(String timerName) {
        Timer timer = new Timer();
        timers.put(timerName, timer);
        timer.start();
        return timer;
    }


    private TimerRegistry() {
        timers = Multimaps.synchronizedMultimap(HashMultimap.create());
    }

    private static TimerRegistry instance = null;

    //design-patterns double-locking
    public static TimerRegistry getInstance(){
        if(instance == null){
            synchronized (TimerRegistry.class) {
                if(instance == null){
                    instance = new TimerRegistry();
                }
            }
        }
        return instance;
    }

}
