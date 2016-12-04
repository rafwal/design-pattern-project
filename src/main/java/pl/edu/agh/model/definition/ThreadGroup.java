package pl.edu.agh.model.definition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ThreadGroup {

    @Id
    @SequenceGenerator(name = "ThreadGroupSequence", sequenceName = "ThreadGroupSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ThreadGroupSequence")
    private Integer id;

    private Integer threadsCount;

    private Integer rampUpPeriod;

    private Integer loopCount;

    private Integer delay;

    @OneToMany(mappedBy = "threadGroup")
    private List<Test> tests;

}
