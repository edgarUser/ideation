package org.ideation.controller;

import java.util.List;
import org.ideation.model.SystemDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResController class.
 */
@RestController
public class PocController {

  @Value("${application.greeting}")
  private String greeting;

  /**
   * Get system details as default.
   *
   * @return SystemDetails system details
   */
  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SystemDetails> root() {
    return new ResponseEntity<>(SystemDetails.builder()
        .availableProcessors(Runtime.getRuntime().availableProcessors())
        .totalMemory(Runtime.getRuntime().totalMemory())
        .maxMemory(Runtime.getRuntime().maxMemory())
        .freeMemory(Runtime.getRuntime().freeMemory())
        .build(), HttpStatusCode.valueOf(200));
  }

  @GetMapping(path = "/details")
  public ResponseEntity<List<String>> getDetails() {
    return new ResponseEntity<>(List.of("Hello World!", greeting), HttpStatusCode.valueOf(200));
  }

}
