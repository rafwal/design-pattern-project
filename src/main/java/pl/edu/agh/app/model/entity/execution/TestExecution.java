package pl.edu.agh.app.model.entity.execution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.app.model.entity.definition.TestDefinition;
import pl.edu.agh.app.model.entity.definition.ThreadGroup;
import pl.edu.agh.app.model.enums.TestExecutionState;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestExecution {

    @Id
    @SequenceGenerator(name = "TestExecutionSequence", sequenceName = "TestExecutionSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TestExecutionSequence")
    private Long id;

    private Instant startTime;

    @Enumerated(EnumType.STRING)
    private TestExecutionState state;

    private Instant endTime;

    //in minutes
    private Long timeout;

    @OneToOne
    @JoinColumn(name = "threadGroupId")
    private ThreadGroup threadGroup;

    @OneToOne
    @JoinColumn(name = "testDefinitionId")
    private TestDefinition testDefinition;
}
