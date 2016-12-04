package pl.edu.agh.model.execution;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SingleExecution {

    @Id
    private Long id;

    private Instant startTime;

    private Instant stopTime;

    @OneToOne   //one-directional relation
    @JoinColumn(name = "executionRequestId")
    private ExecutionRequest executionRequest;

    @OneToOne   //one-directional relation
    @JoinColumn(name = "executionResponseId")
    private ExecutionResponse executionResponse;

    @ManyToOne
    @JoinColumn(name = "testExecutionId")
    private TestExecution testExecution;
}
