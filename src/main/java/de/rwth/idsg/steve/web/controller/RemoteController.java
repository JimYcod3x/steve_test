package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService12_Client;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStopTransactionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/manager/remoteController")
public class RemoteController extends Ocpp16Controller{


    @Autowired
    @Qualifier("ChargePointService12_Client")
    private ChargePointService12_Client client12;

    private static final String START_PATH = "/start";
    private static final String STOP_PATH = "/stop";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    protected static final String START_PARAMS = "startParams";
    protected static final String STOP_PARAMS = "stopParams";

    protected ChargePointService12_Client getClient12() {
        return client12;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getRemoteStartTx(Model model) {
        setCommonAttributesForTx(model);
        setActiveUserIdTagList(model);
        model.addAttribute(START_PARAMS, new RemoteStartTransactionParams());
        model.addAttribute(START_PARAMS, new RemoteStopTransactionParams());
        return "remoteController";
    }

    @RequestMapping(value =  START_PATH, method = RequestMethod.POST)
    public String postRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams startParams,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            setCommonAttributesForTx(model);
            setActiveUserIdTagList(model);
            return START_PATH;
        }
        getClient12().remoteStartTransaction(startParams);
        return START_PATH;
    }

    @RequestMapping(value =  STOP_PATH, method = RequestMethod.POST)
    public String postRemoteStartTx(@Valid @ModelAttribute(STOP_PARAMS) RemoteStopTransactionParams stopParams,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            setCommonAttributesForTx(model);
            setActiveUserIdTagList(model);
            return STOP_PATH;
        }
        getClient12().remoteStopTransaction(stopParams);
        return STOP_PATH;
    }
}

