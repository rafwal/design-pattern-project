package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.model.ThreadGroup;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadGroupDTO {


    private Integer id;
    private Integer threadsCount;
    private Integer rampUpPeriod;
    private Integer loopCount;
    private Integer delay;

    public static ThreadGroupDTO toDTO(ThreadGroup threadGroup) {
        return ThreadGroupDTO.builder()
                .id(threadGroup.getId())
                .loopCount(threadGroup.getLoopCount())
                .rampUpPeriod(threadGroup.getRampUpPeriod())
                .threadsCount(threadGroup.getThreadsCount())
                .delay(threadGroup.getDelay())
                .build();
    }

    public static ThreadGroup toEntity(ThreadGroupDTO dto) {
        return ThreadGroup.builder()
                .id(dto.getId())
                .loopCount(dto.getLoopCount())
                .rampUpPeriod(dto.getRampUpPeriod())
                .threadsCount(dto.getThreadsCount())
                .delay(dto.getDelay())
                .build();
    }
}
