package pl.edu.agh.app.lib.metrics.timers;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalField;

public class Timer implements AutoCloseable {

    private Instant startTime;
    private Instant endTime;

    public void start() {
        startTime = Instant.now();
    }

    public void close() {
        endTime = Instant.now();
    }


    //methods used by jackson mapper

    public Long getStartTime() {
        return startTime.toEpochMilli();
    }

    public Long getEndTime() {
        return endTime.toEpochMilli();
    }
}


