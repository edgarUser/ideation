package org.ideation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
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

  @GetMapping(path = "/details")
  public ResponseEntity<List<String>> getDetails() {
    return new ResponseEntity<>(List.of("Hello World!", greeting), HttpStatusCode.valueOf(200));
  }

}
