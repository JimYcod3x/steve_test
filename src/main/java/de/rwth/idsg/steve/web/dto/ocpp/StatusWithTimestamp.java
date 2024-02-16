package de.rwth.idsg.steve.web.dto.ocpp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StatusWithTimestamp {
    private String status;
    private LocalDateTime statusTimestamps;
}
