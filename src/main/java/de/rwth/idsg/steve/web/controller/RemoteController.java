package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService12_Client;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStopTransactionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/manager")
public class RemoteController extends Ocpp16Controller{


    @Autowired
    @Qualifier("ChargePointService12_Client")
    private ChargePointService12_Client client12;
    private static final String START_PATH = "start";
    private static final String STOP_PATH = "stop";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    protected static final String START_PARAMS = "startParams";
    protected static final String STOP_PARAMS = "stopParams";

    protected ChargePointService12_Client getClient12() {
        return client12;
    }

    @GetMapping( "/remoteController")
    public String myGetController(Model model) {
        setCommonAttributesForTx(model);
        setActiveUserIdTagList(model);
        model.addAttribute(START_PARAMS, new RemoteStartTransactionParams());
        model.addAttribute(STOP_PARAMS, new RemoteStopTransactionParams());
        return "remoteController";
    }

    @PostMapping( "/remoteController/")
    public void myPostRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams startParams,
                                    BindingResult result, Model model) {
        log.info("Received form parameters: {}", startParams);
        if (result.hasErrors()) {
            setCommonAttributesForTx(model);
            setActiveUserIdTagList(model);


        }

        getClient12().remoteStartTransaction(startParams);
    }
    @PostMapping("/remoteController/" + STOP_PATH)
    public String myPostRemoteStopTx(@Valid @ModelAttribute(STOP_PARAMS) RemoteStopTransactionParams stopParams,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            setCommonAttributesForTx(model);
            setActiveUserIdTagList(model);
            return STOP_PATH;
        }
        getClient12().remoteStopTransaction(stopParams);
        return "remoteController";
    }
}

