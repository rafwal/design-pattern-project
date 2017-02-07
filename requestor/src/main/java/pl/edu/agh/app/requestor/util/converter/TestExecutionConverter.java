package pl.edu.agh.app.requestor.util.converter;


import pl.edu.agh.app.requestor.dto.TestExecutionDTO;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;

public class TestExecutionConverter {

    public static TestExecutionDTO toDto(TestExecution testExecution) {
        return TestExecutionDTO.builder()
                .id(testExecution.getId())
                .endTime(testExecution.getEndTime())
                .state(testExecution.getState())
                .startTime(testExecution.getStartTime())
                .timeout(testExecution.getTimeout())
                .testDefinitionId(testExecution.getTestDefinition().getId())
                .threadGroupId(testExecution.getThreadGroup().getId())
                .build();
    }

}
