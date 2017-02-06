package pl.edu.agh.listener;

import lombok.Data;
import lombok.Setter;
import pl.edu.agh.app.model.entity.execution.Timer;

import java.time.Duration;
import java.time.Instant;

@Data
public class TimerDTO {

    private Long startTime;

    private Long endTime;

    public Timer toEntity() {

        return  Timer.builder()
                .startTime(Instant.ofEpochMilli(this.startTime))
                .endTime(Instant.ofEpochMilli(this.endTime))
                .build();
    }

}


