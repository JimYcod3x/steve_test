package de.rwth.idsg.steve.web.api;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(value = "/api/my/remote/", produces = MediaType.APPLICATION_JSON_VALUE ,method=RequestMethod.POST)

@RequiredArgsConstructor
public class RemoteRestController {
  
}
