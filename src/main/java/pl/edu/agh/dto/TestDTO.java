package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import pl.edu.agh.model.definition.Test;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Integer id;

    private Integer threadGroupId;

    private String url;

    private HttpMethod method;

    private String body;

    private Map<String,String> headers;

    public static TestDTO toDTO(Test test) {
        return TestDTO.builder()
                .id(test.getId())
                .body(test.getBody())
                .method(test.getMethod())
                .url(test.getUrl())
                .headers(test.getHeaders())
                .build();
    }

    public static Test toEntity(TestDTO request) {
        return Test.builder()
                .id(request.getId())
                .body(request.getBody())
                .method(request.getMethod())
                .url(request.getUrl())
                .headers(request.getHeaders())
                .build();
    }
}
