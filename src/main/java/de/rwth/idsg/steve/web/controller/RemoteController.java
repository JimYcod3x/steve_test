package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.web.dto.ocpp.CombineStartStopParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/manager")
public class RemoteController extends Ocpp16Controller{


    private static final String REMOTE_PATH = "/remoteController";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    protected static final String PARAMS = "params";
    protected String getPrefix() {
        return "op16";
    }

    @RequestMapping(value = REMOTE_PATH, method = RequestMethod.GET)
    public String getRemoteStartTx(Model model) {
        setCommonAttributesForTx(model);
        setActiveUserIdTagList(model);
        model.addAttribute(PARAMS, new CombineStartStopParams());
        return "remoteController";
    }
}

