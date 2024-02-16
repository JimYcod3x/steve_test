package de.rwth.idsg.steve.web.controller;


import de.rwth.idsg.steve.ocpp.ChargePointService12_Invoker;
import de.rwth.idsg.steve.ocpp.ChargePointService12_InvokerImpl;
import de.rwth.idsg.steve.ocpp.OcppVersion;
import de.rwth.idsg.steve.repository.ChargePointRepository;
import de.rwth.idsg.steve.repository.GenericRepository;
import de.rwth.idsg.steve.repository.TaskStore;
import de.rwth.idsg.steve.repository.TransactionRepository;
import de.rwth.idsg.steve.repository.dto.TransactionDetails;
import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService12_Client;
import de.rwth.idsg.steve.web.dto.ocpp.StartStopParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@Controller
@RequestMapping(value = "/manager")
public class RemoteController extends Ocpp16Controller {


    @Autowired
    @Qualifier("ChargePointService12_Client")
    private ChargePointService12_Client client12;
    private static final String REMOTE_PATH = "/remoteController";
    private static final String REDIRECT_PATH = "redirect:/manager/remoteController";
    private static final String START_PATH = "/start";


    private static final String STOP_PATH = "/stop";
    @Autowired
    protected ChargePointHelperService chargePointHelperService;
    protected static final String START_STOP_PARAMS = "startStopParams";
    protected static final String STOP_PARAMS = "stopParams";

    @Autowired
    protected TaskStore taskStore;

    @Autowired
    protected TransactionRepository transactionRepository;

    @Autowired
    protected ChargePointRepository chargePointRepository;

    protected OcppVersion getVersion() {
        return OcppVersion.V_12;
    }

    @Autowired
    private ChargePointService12_InvokerImpl invoker12;
    @Autowired
    private GenericRepository genericRepository;

    protected ChargePointService12_Invoker getOcpp12Invoker() {
        return invoker12;
    }

    @Autowired
    protected ScheduledExecutorService executorService;

    protected ChargePointService12_Client getClient12() {
        return client12;
    }

    private List<TransactionDetails.MeterValues> metaValueList;

    private Map<String, String> transactionDetails;

    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public String getTransactionId() {
        transactionDetails = transactionRepository.getAllStartStopDetails();
        return transactionDetails.get("transaction_pk");
    }

    public Duration getTimeDelta() {
        transactionDetails = transactionRepository.getAllStartStopDetails();
        LocalDateTime starttime = LocalDateTime.parse(transactionDetails.get("start_timestamp"), myFormatObj);
        LocalDateTime timeNow = LocalDateTime.from(LocalDateTime.now());
        Duration timeDelta = Duration.between(starttime, timeNow);
        long seconds = timeDelta.getSeconds() % 60;
        long minutes = timeDelta.toMinutes() % 60;
        long hours = timeDelta.toHours();

        log.info("seconds" + seconds + "minutes" + minutes + "hours" + hours);
        return timeDelta;
    }


    public String getStatus() {
        transactionDetails = transactionRepository.getAllStartStopDetails();
        String status = chargePointRepository.getStatusForConnectorId(chargePointHelperService.getChargePoints(OcppVersion.V_16).getFirst().getChargeBoxId()).getFirst().getStatus();
        for (Map.Entry<String, String> entry : transactionDetails.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String entryType = entry.getClass().getName();
            log.info("Key: " + key + ", Value: " + value + "Type: " + value.getClass().getName());
        }
        return status;
    }


    public String getPower() {
        transactionDetails = transactionRepository.getAllStartStopDetails();
        if(transactionDetails.get("MetaValue") != null && transactionDetails.get("MetaUnit") != null)
            return transactionDetails.get("MetaValue") + " " + transactionDetails.get("MetaUnit");
        return "0 W";
    }

        @GetMapping(REMOTE_PATH)
        public String myGetController (Model model){

            String chargePointId = chargePointHelperService.getChargePoints(OcppVersion.V_16).getFirst().getChargeBoxId();
            String status = chargePointRepository.getStatusForConnectorId(chargePointId).getFirst().getStatus();
            getStatus();
            LocalDateTime starttime = chargePointRepository.getStatusForConnectorId(chargePointId).getFirst().getStatusTimestamps();
            log.info("new" + status);
            log.info("startTime:" + getTimeDelta());
            log.info("getpower: " + getPower());
//        log.info("status" + latestList);
//        log.info("current status: " + latestList.getFirst().getStatus());
//        log.info("current chargeBoxid: " + latestList.getFirst().getChargeBoxId());
//        log.info("current pk: " + latestList.getFirst().getChargeBoxPk());
//        log.info("current connectorId: " + latestList.getFirst().getConnectorId());
//        log.info("current connectorId: " + latestList.getFirst().getStatusTimestamp());

//        String status = latestList.getFirst().getStatus();
//        for (ConnectorStatus status : latestList) {
//            log.info("this the status:" + String.valueOf(status.getStatus()));
//        }
//        for (ConnectorStatus status : latestList) {
//            log.info(String.valueOf(status.getStatus()));
//        }
//        log.info(String.valueOf(latestList));
            setCommonAttributesForTx(model);
            setActiveUserIdTagList(model);
            model.addAttribute(START_STOP_PARAMS, new StartStopParams());


//        for (TransactionDetails.MeterValues meterValues : metaValueList) {
//            log.info(meterValues.getValue());
//            log.info(meterValues.getMeasurand());
//            log.info(String.valueOf(meterValues.getValueTimestamp()));
//            log.info(meterValues.getUnit());
//
//        }


//        if (getStatus() == "Charging") {
//            model.addAttribute()
//        }
//        try {
//            transactionDetails = transactionRepository.getAllStartStopDetails();
//            LocalDateTime startTimeStamp = LocalDateTime.parse(transactionDetails.get("start_timestamp"), myFormatObj);
//            log.info("timeof" + (startTimeStamp));
//        } catch (DateTimeParseException e) {
//            log.info("Error parsing LocalDateTime: " + e.getMessage());
//        }


//
//        LocalDateTime timeNow = LocalDateTime.from(LocalTime.now());
//        LocalDateTime startTimeStamp = LocalDateTime.parse(transactionDetails.get("start_timestamp"), myFormatObj);
//        Duration timeDelta = Duration.between(startTimeStamp, timeNow);
//        long seconds = timeDelta.getSeconds();
//        long minutes = timeDelta.toMinutes();
//        long hours = timeDelta.toHours();
//        if (Objects.equals(getStatus(), "Charging")) {
//            model.addAttribute("seconds", seconds);
//            model.addAttribute("minutes", minutes);
//            model.addAttribute("hours", hours);


//        }
            if (Objects.equals(status, "Charging")) {
                long seconds = getTimeDelta().getSeconds() % 60;
                long minutes = getTimeDelta().toMinutes() % 60;
                long hours = getTimeDelta().toHours();

                model.addAttribute("seconds", seconds);
                model.addAttribute("minutes", minutes);
                model.addAttribute("hours", hours);
            }
            model.addAttribute("transactionId", getTransactionId());
//            model.addAttribute("status", status);
            return "remoteController";
        }

        @ResponseBody
        @GetMapping(value = "/fetch-update", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<Map<String, Object>> fetchUpdate () {
            return Flux.interval(Duration.ofSeconds(1)) // Emit events at 1-second intervals
                    .map(sequence -> {
                        // Perform logic to fetch update data
                        String power = getPower(); // Fetch power data
                        String status = getStatus(); // Fetch status data

                        long seconds = getTimeDelta().getSeconds() % 60;
                        long minutes = getTimeDelta().toMinutes() % 60;
                        long hours = getTimeDelta().toHours();

                        String secondsStr = String.format("%02d", seconds);
                        String minutesStr = String.format("%02d", minutes);
                        String hoursStr = String.format("%02d", hours);

                        Map<String, Object> eventData = new HashMap<>();
                        eventData.put("power", power);
                        eventData.put("status", status);
                        eventData.put("seconds", secondsStr);
                        eventData.put("minutes", minutesStr);
                        eventData.put("hours", hoursStr);

                        return eventData;
                    });
        }


//    public UpdateResponse fetchUpdate() {
//        UpdateResponse response = new UpdateResponse();
//
//
//
//        if (metaValueList != null && metaValueList.size() > 1) {
//            String instancePowerValue = String.valueOf(metaValueList.get(1).getValue());
//            String instancePowerUnit = String.valueOf(metaValueList.get(1).getUnit());
//
//            String power = instancePowerValue + instancePowerUnit;
//            response.setPower(power);
//        }
//
//        response.setStatus(getStatus());
//
//        return response;
//    }
    }

