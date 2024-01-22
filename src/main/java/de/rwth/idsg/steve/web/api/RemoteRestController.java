//package de.rwth.idsg.steve.web.api;
//
//import de.rwth.idsg.steve.repository.dto.Transaction;
//import de.rwth.idsg.steve.web.api.exception.BadRequestException;
//import de.rwth.idsg.steve.web.dto.TransactionQueryForm;
//import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.extern.slf4j.Slf4j;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//
//import javax.validation.Valid;
//import java.util.List;
//
//
//@Slf4j
//@RestController
//@RequestMapping(value = "/api/v1/remote/", produces = MediaType.APPLICATION_JSON_VALUE ,method=RequestMethod.GET)
//@RequiredArgsConstructor
//public class RemoteRestController {
//
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
//            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
//    )
//    @GetMapping(value = "")
//    @ResponseBody
//    public List<Transaction> get(@Valid TransactionQueryForm.ForApi params) {
//        log.debug("Read request for query: {}", params);
//
//        if (params.isReturnCSV()) {
//            throw new BadRequestException("returnCSV=true is not supported for API calls");
//        }
//
//        var response = transactionRepository.getTransactions(params);
//        log.debug("Read response for query: {}", response);
//        return response;
//    }
//}
