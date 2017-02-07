package pl.edu.agh.app.requestor.util.converter;


import pl.edu.agh.app.requestor.dto.SingleExecutionDTO;
import pl.edu.agh.app.requestor.model.entity.execution.SingleExecution;

import java.time.Duration;

public class SingleTestExecutionConverter {
    public static SingleExecutionDTO toDTO(SingleExecution entity) {
        return SingleExecutionDTO.builder()
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .threadNo(entity.getThreadNo())
                .output(entity.getOutput())
                .duration(Duration.between(entity.getStartTime(),entity.getEndTime()).toMillis())
                .build();
    }
}
