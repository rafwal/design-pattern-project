package pl.edu.agh.app.requestor.dto;

import lombok.Data;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

import java.time.Instant;
import java.util.Map;

@Data
public class AverageResultsDTO {
    private String appName;
    private Instant startTime;
    private Instant endTime;
    private Long duration;

    private Long averageSingleExecution;

    private Map<String, Long> averageTimers;
}
