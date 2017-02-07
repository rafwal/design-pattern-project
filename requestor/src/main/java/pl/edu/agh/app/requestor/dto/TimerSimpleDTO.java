package pl.edu.agh.app.requestor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerSimpleDTO {
    private Instant startTime;

    private Instant endTime;

    private Long duration;

}
