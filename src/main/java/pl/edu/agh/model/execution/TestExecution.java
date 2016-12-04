package pl.edu.agh.model.execution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.model.definition.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestExecution {

    @Id
    private Long id;

    private Instant startTime;

    private Instant stopTime;

    @OneToMany(mappedBy = "testExecution")
    private List<SingleExecution> singleExecutions;

    @ManyToOne
    @JoinColumn(name = "testId")
    private Test test;

}
