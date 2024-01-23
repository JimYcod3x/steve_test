package de.rwth.idsg.steve.web.dto.ocpp;

import lombok.Getter;

@Getter
public class CombineStartStopParams extends SingleChargePointSelect {

    private RemoteStartTransactionParams remoteStartTransactionParams;
    private RemoteStopTransactionParams remoteStopTransactionParams;

    public CombineStartStopParams() {
        this.remoteStartTransactionParams = new RemoteStartTransactionParams();
        this.remoteStopTransactionParams = new RemoteStopTransactionParams();
    }
}
