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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Timer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TimerSequence")
    @SequenceGenerator(name = "TimerSequence", sequenceName = "TimerSequence", allocationSize = 1)
    private Long id;

    private Instant startTime;
    private Instant endTime;
    private String name;

    @OneToOne
    @JoinColumn(name = "testExecutionId")
    private TestExecution testExecution;

}
