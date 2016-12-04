package pl.edu.agh.model.definition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import pl.edu.agh.model.definition.ThreadGroup;
import pl.edu.agh.model.execution.TestExecution;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Test {

    @Id
    @SequenceGenerator(name = "TestSequence", sequenceName = "TestSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TestSequence")
    private Integer id;

    private String url;

    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    private String body;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Header", joinColumns = @JoinColumn(name = "testid"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String,String> headers;

    @ManyToOne
    @JoinColumn(name = "threadGroupId")
    private ThreadGroup threadGroup;

    @OneToMany(mappedBy = "test")
    private List<TestExecution> testExecutions;

}
