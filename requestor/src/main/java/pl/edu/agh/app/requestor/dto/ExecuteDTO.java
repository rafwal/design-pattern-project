package pl.edu.agh.app.requestor.dto;

import lombok.Data;


@Data
//design-pattern DTO
public class ExecuteDTO {
    private Long testDefinitionId;
    private Long threadGroupId;
    private Long delayBeforeClosingAcceptor;
    private Long timeout;
}
