package pl.edu.agh.app.requestor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.app.requestor.model.enums.TestExecutionState;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TestExecutionDTO {

    private Long id;

    private Instant startTime;

    private TestExecutionState state;

    private Instant endTime;

    //in minutes
    private Long timeout;

    private Long threadGroupId;

    private Long testDefinitionId;
}
