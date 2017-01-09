package pl.edu.agh.app.plugins.execution.http;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.app.plugins.execution.interfaces.Task;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpTask implements Task<HttpEntityDefinition> {

    private RestTemplate restTemplate;

    {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        restTemplate = new RestTemplate(clientHttpRequestFactory);
    }


    @Override
    public String execute(HttpEntityDefinition definition) {
        RequestEntity<String> requestEntity =
                new RequestEntity<>(definition.getBody(),
                        definition.getMethod(),
                        getUri(definition.getUrl()));
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        return response.getStatusCode().toString();
    }

    private URI getUri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }
}
