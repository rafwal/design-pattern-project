package pl.edu.agh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

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
import javax.persistence.SequenceGenerator;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {

    @Id
    @SequenceGenerator(name = "RequestSequence", sequenceName = "RequestSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestSequence")
    private Integer id;

    private String url;

    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    private String body;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Header", joinColumns = @JoinColumn(name = "requestid"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String,String> headers;

    @ManyToOne
    @JoinColumn(name = "threadGroupId")
    private ThreadGroup threadGroup;

}
