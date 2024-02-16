package de.rwth.idsg.steve.web.dto.ocpp;

import de.rwth.idsg.steve.web.validation.IdTag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
public class StartStopParams extends SingleChargePointSelect {

//    @Min(value = 0, message = "Connector ID must be at least {value}")
//    private Integer connectorId;

    @NotBlank(message = "User ID Tag is required")
    @IdTag
    @Setter
    private String idTag;
//    private Integer transactionId;
}
