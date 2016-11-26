package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import pl.edu.agh.model.Request;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private Integer id;

    private Integer threadGroupId;

    private String url;

    private HttpMethod method;

    private String body;

    private Map<String,String> headers;

    public static RequestDTO toDTO(Request request) {
        return RequestDTO.builder()
                .id(request.getId())
                .body(request.getBody())
                .method(request.getMethod())
                .url(request.getUrl())
                .headers(request.getHeaders())
                .build();
    }

    public static Request toEntity(RequestDTO request) {
        return Request.builder()
                .id(request.getId())
                .body(request.getBody())
                .method(request.getMethod())
                .url(request.getUrl())
                .headers(request.getHeaders())
                .build();
    }
}
