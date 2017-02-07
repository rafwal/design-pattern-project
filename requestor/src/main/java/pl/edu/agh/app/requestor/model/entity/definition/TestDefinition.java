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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TestDefinition {

    @Id
    @SequenceGenerator(name = "TestSequence", sequenceName = "TestSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TestSequence")
    private Long id;

    private String json;

    private String pluginName;
}
