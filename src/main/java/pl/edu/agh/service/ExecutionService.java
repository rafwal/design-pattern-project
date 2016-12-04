package pl.edu.agh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.model.definition.Test;
import pl.edu.agh.repository.TestRepository;

import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class ExecutionService {

    private RestTemplate restTemplate;

    @Autowired
    private TestRepository testRepository;

    public void execute(Integer testId) {

        Test test = testRepository.findById(testId).orElseThrow(RuntimeException::new);

        try {
            RequestEntity<String> requestEntity =
                    new RequestEntity<>(test.getBody(), test.getMethod(), new URI(test.getUrl()));

            ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        } catch (Exception e) {
            System.err.println("Error " + e.getClass());
        }

    }

    @PostConstruct
    void restTemplate() {
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        //TODO as property
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
