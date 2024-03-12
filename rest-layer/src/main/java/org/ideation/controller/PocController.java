package org.ideation.controller;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResController class.
 */
@RestController
public class PocController {

  @GetMapping(path = "/details")
  public ResponseEntity<List<String>> getDetails() {
    return new ResponseEntity<>(List.of("Hello"), HttpStatusCode.valueOf(200));
  }

}
