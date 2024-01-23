package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService12_Client;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStopTransactionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/manager")
public class RemoteController extends Ocpp16Controller{


    @Autowired
    @Qualifier("ChargePointService12_Client")
    private ChargePointService12_Client client12;

    private static final String REMOTE_PATH = "/remoteController";
    private static final String START_PATH = "/start";
    private static final String STOP_PATH = "/stop";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    protected static final String START_PARAMS = "startParams";
    protected static final String STOP_PARAMS = "stopParams";

    protected ChargePointService12_Client getClient12() {
        return client12;
    }

    @GetMapping( REMOTE_PATH)
    public String myGetRemoteStartTx(Model model) {
        setCommonAttributesForTx(model);
        setActiveUserIdTagList(model);
        model.addAttribute(START_PARAMS, new RemoteStartTransactionParams());
        model.addAttribute(STOP_PARAMS, new RemoteStopTransactionParams());
        return "remoteController";
    }

    @PostMapping( REMOTE_PATH + START_PATH)
    public ResponseEntity<String> myPostRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams startParams,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok("The ChargePoint is fail to run");
        }
        getClient12().remoteStartTransaction(startParams);
        return ResponseEntity.ok("The ChargePoint is running");
    }

    @PostMapping(REMOTE_PATH + STOP_PATH)
    public ResponseEntity<String> myPostRemoteStartTx(@Valid @ModelAttribute(STOP_PARAMS) RemoteStopTransactionParams stopParams,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        getClient12().remoteStopTransaction(stopParams);
        return ResponseEntity.ok("The ChargePoint is stopped");
    }
}

