package de.rwth.idsg.steve.web.api;

import org.springframework.web.bind.annotation.RestController;

import de.rwth.idsg.steve.repository.dto.OcppTag;
import de.rwth.idsg.steve.service.BackgroundService;
import de.rwth.idsg.steve.web.api.ApiControllerAdvice.ApiErrorResponse;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import de.rwth.idsg.steve.web.controller.Ocpp12Controller;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/remote", produces = MediaType.APPLICATION_JSON_VALUE)
public class RemoteRestController extends Ocpp12Controller{
  

  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = ApiErrorResponse.class),
    @ApiResponse(code = 404, message = "Not Found", response = ApiErrorResponse.class),
    @ApiResponse(code = 500, message = "Internal Server Error", response = ApiErrorResponse.class)}
)
@GetMapping("/startTransaction")
    @ResponseBody
    public void postMyStart(@Valid @ModelAttribute("params") RemoteStartTransactionParams params, @RequestParam Integer connectorId,@RequestParam String idTag, BindingResult result, Model model) {
      params.setConnectorId(connectorId);
      params.setIdTag(idTag);

      if (result.hasErrors()) {
        log.error("Error in startTransaction: {}", result.getAllErrors());
        return;
      }
       getClient12().remoteStartTransaction(params);
    }



}
