package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/remoteController")
public class RemoteController {


    private static final String START_TX_PATH = "/StartTransaction";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    @Autowired protected Ocpp16Controller ocpp16Controller;
    protected static final String PARAMS = "params";
    protected String getPrefix() {
        return "op16";
    }

    @RequestMapping(value = START_TX_PATH, method = RequestMethod.GET)
    public String getRemoteStartTx(Model model) {
        ocpp16Controller.setCommonAttributesForTx(model);
        ocpp16Controller.setActiveUserIdTagList(model);
        model.addAttribute(PARAMS, new RemoteStartTransactionParams());
        return getPrefix() + START_TX_PATH;
    }
}

