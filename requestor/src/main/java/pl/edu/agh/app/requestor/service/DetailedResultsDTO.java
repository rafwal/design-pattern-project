package pl.edu.agh.app.requestor.service;


import lombok.Data;
import pl.edu.agh.app.requestor.dto.SingleExecutionDTO;
import pl.edu.agh.app.requestor.dto.TimerSimpleDTO;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class DetailedResultsDTO {

    private String appName;
    private Instant startTime;
    private Instant endTime;
    private Long duration;

    private List<SingleExecutionDTO> singleExecutions;

    private Map<String, Collection<TimerSimpleDTO>> timers;
}
