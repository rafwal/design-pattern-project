package pl.edu.agh.app.requestor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleExecutionDTO {

    private Integer threadNo;

    private Instant startTime;

    private Instant endTime;

    private Long duration;

    private String output;

}
