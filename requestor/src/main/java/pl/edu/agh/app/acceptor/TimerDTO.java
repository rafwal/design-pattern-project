package pl.edu.agh.app.acceptor;

import lombok.Data;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

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


