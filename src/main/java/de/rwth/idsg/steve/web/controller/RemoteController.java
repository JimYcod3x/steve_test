//package de.rwth.idsg.steve.web.controller;
//
//
//import de.rwth.idsg.steve.ocpp.ChargePointService12_Invoker;
//import de.rwth.idsg.steve.ocpp.ChargePointService12_InvokerImpl;
//import de.rwth.idsg.steve.ocpp.OcppVersion;
//import de.rwth.idsg.steve.repository.TaskStore;
//import de.rwth.idsg.steve.repository.TransactionRepository;
//import de.rwth.idsg.steve.service.ChargePointHelperService;
//import de.rwth.idsg.steve.service.ChargePointService12_Client;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.ScheduledExecutorService;
//
//@Slf4j
//@Controller
////@RequestMapping()
//public class RemoteController extends Ocpp16Controller {
//
//
//    @Autowired
//    @Qualifier("ChargePointService12_Client")
//    private ChargePointService12_Client client12;
//    private static final String REMOTE_PATH = "/remoteController";
//    private static final String REDIRECT_PATH = "redirect:/manager/remoteController";
//    private static final String START_PATH = "/start";
//
//
//    private static final String STOP_PATH = "/stop";
//    @Autowired
//    protected ChargePointHelperService chargePointHelperService;
//    protected static final String START_STOP_PARAMS = "startStopParams";
//    protected static final String STOP_PARAMS = "stopParams";
//
//    @Autowired
//    protected TaskStore taskStore;
//
//    @Autowired
//    protected TransactionRepository transactionRepository;
//
//    protected OcppVersion getVersion() {
//        return OcppVersion.V_12;
//    }
//
//    @Autowired
//    private ChargePointService12_InvokerImpl invoker12;
//
//    protected ChargePointService12_Invoker getOcpp12Invoker() {
//        return invoker12;
//    }
//
//    @Autowired
//    protected ScheduledExecutorService executorService;
//
//    protected ChargePointService12_Client getClient12() {
//        return client12;
//    }
//
//    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//
//
//
//
////    @ResponseBody
////    @RequestMapping(value = "/remoteController", method = RequestMethod.POST)
////    public String postRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams params,
////                                    BindingResult result, Model model) {
////        if (result.hasErrors()) {
////            setCommonAttributesForTx(model);
////            setActiveUserIdTagList(model);
////            return "/remoteController";
////        }
////        getClient12().remoteStartTransaction(params);
////        return "/remoteController";
////    }
//
//
//    //    @PostMapping( "/remoteController/start")
////    public String myPostRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams startParams,
////                                    BindingResult result, Model model) {
////        log.info("Received form parameters: {}", startParams);
////        if (result.hasErrors()) {
////            setCommonAttributesForTx(model);
////            setActiveUserIdTagList(model);
////
////
////        }
////
////        getClient12().remoteStartTransaction(startParams);
////        return "remoteController/start";
////    }
//
////        @PostMapping(REMOTE_PATH + START_PATH)
////        public String postMyRemoteStartTx(@Valid @ModelAttribute(START_PARAMS) RemoteStartTransactionParams params,
////                                          BindingResult result, Model model) {
////            if (result.hasErrors()) {
////                setCommonAttributesForTx(model);
////                setActiveUserIdTagList(model);
////                return "remoteController";
////            }
////
////            getClient12().remoteStartTransaction(params);
////            return "redirect:remoteController";
////        }
////
////    @ResponseBody
////    @PostMapping(REMOTE_PATH + STOP_PATH)
////    public String postMyRemoteStopTx(@Valid @ModelAttribute(STOP_PARAMS) RemoteStopTransactionParams params,
////                                     BindingResult result, Model model) {
////        if (result.hasErrors()) {
////            setCommonAttributesForTx(model);
////            return "remoteController";
////        }
////
////        getClient12().remoteStopTransaction(params);
////        return REDIRECT_PATH ;
////    }
//}
//
