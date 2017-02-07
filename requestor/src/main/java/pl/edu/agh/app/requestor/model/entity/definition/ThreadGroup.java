package pl.edu.agh.app.requestor.model.entity.definition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ThreadGroup {

    @Id
    @SequenceGenerator(name = "ThreadGroupSequence", sequenceName = "ThreadGroupSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ThreadGroupSequence")
    private Long id;

    private Integer threadsCount;

    private Integer loopCount;

    private Integer delay;

}
