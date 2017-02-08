package pl.edu.agh.app.lib.metrics.timers;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

//design-pattern singleton
public class TimerRegistry {


    private Multimap<String, Timer> timers = Multimaps.synchronizedMultimap(HashMultimap.create());


    public synchronized void assignTimersAndReset(Multimap<String, Timer> timers) {
        timers.putAll(this.timers);
        this.timers.clear();
    }


    public Timer timer(String timerName) {
        Timer timer = new Timer();
        timers.put(timerName, timer);
        timer.start();
        return timer;
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
