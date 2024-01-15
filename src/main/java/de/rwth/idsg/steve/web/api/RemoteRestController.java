package de.rwth.idsg.steve.web.api;

import org.springframework.web.bind.annotation.RestController;

import de.rwth.idsg.steve.repository.dto.OcppTag;
import de.rwth.idsg.steve.service.BackgroundService;
import de.rwth.idsg.steve.web.api.ApiControllerAdvice.ApiErrorResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import de.rwth.idsg.steve.service.BackgroundService;

@RestController
@RequestMapping(value = "/api/v1/remote", produces = MediaType.APPLICATION_JSON_VALUE)
public class RemoteRestController {
  
  private final BackgroundService backgroundService;

  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = ApiErrorResponse.class),
    @ApiResponse(code = 404, message = "Not Found", response = ApiErrorResponse.class),
    @ApiResponse(code = 500, message = "Internal Server Error", response = ApiErrorResponse.class)}
)
@GetMapping("/{startTransaction}")
    @ResponseBody
    public OcppTag.Overview getOne(@PathVariable("startTransaction") String startTransaction) {
        log.debug("Read request for startTransaction: {}", startTransaction);

        var response = getOneInternal(startTransaction);
        log.debug("Read response: {}", response);
        return response;
    }



}
