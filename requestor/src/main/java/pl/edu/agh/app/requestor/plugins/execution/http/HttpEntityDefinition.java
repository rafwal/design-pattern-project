package pl.edu.agh.app.requestor.plugins.execution.http;

import lombok.Data;
import org.springframework.http.HttpMethod;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.EntityDefinition;

import java.util.Map;

@Data
public class HttpEntityDefinition implements EntityDefinition {

    private String url;

    private HttpMethod method;

    private String body;

    private Map<String,String> headers;

}
