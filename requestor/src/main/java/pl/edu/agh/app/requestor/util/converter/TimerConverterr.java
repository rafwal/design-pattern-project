package pl.edu.agh.app.requestor.util.converter;


import pl.edu.agh.app.requestor.dto.TimerSimpleDTO;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

import java.time.Duration;

public class TimerConverterr {

    public static TimerSimpleDTO toSimpleDTO(Timer timer) {
        return TimerSimpleDTO.builder()
                .startTime(timer.getStartTime())
                .endTime(timer.getEndTime())
                .duration(Duration.between(timer.getStartTime(), timer.getEndTime()).toMillis())
                .build();
    }
}
