package pl.edu.agh.app.model.dto;

import lombok.Data;

@Data
public class ExecuteDTO {
    private Long testDefinitionId;
    private Long threadGroupId;
    private Long timeout;
}
