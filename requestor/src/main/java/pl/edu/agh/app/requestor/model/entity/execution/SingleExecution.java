package pl.edu.agh.app.requestor.model.entity.execution;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SingleExecution {

    @Id
    @SequenceGenerator(sequenceName = "SingleExecutionSequence", name = "SingleExecutionSequence", allocationSize = 1)
    @GeneratedValue(generator = "SingleExecutionSequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer threadNo;

    private Instant startTime;

    private Instant endTime;

    private String output;

    @ManyToOne
    @JoinColumn(name = "testExecutionId")
    private TestExecution testExecution;
}
