package org.ideation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.ideation.UnitTestProfile;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HealthEndpointTest extends UnitTestProfile {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ApplicationAvailability applicationAvailability;

  @Autowired
  private ApplicationContext context;

  @Test
  @Order(1)
  void liveNessOk() throws Exception {
    assertThat(applicationAvailability.getLivenessState()).isEqualTo(LivenessState.CORRECT);
    mvc.perform(MockMvcRequestBuilders.get("/actuator/health/liveness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  @Order(3)
  void liveNessNoOk() throws Exception {
    AvailabilityChangeEvent.publish(context, LivenessState.BROKEN);
    assertThat(applicationAvailability.getLivenessState()).isEqualTo(LivenessState.BROKEN);
    mvc.perform(MockMvcRequestBuilders.get("/actuator/health/liveness"))
        .andExpect(status().isServiceUnavailable())
        .andExpect(jsonPath("$.status").value("DOWN"));
  }

  @Test
  @Order(2)
  void readinessOk() throws Exception {
    assertThat(applicationAvailability.getReadinessState()).isEqualTo(ReadinessState.ACCEPTING_TRAFFIC);
    mvc.perform(MockMvcRequestBuilders.get("/actuator/health/readiness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  @Order(4)
  void readinessNoOk() throws Exception {
    AvailabilityChangeEvent.publish(context, ReadinessState.REFUSING_TRAFFIC);
    assertThat(applicationAvailability.getReadinessState()).isEqualTo(ReadinessState.REFUSING_TRAFFIC);
    mvc.perform(MockMvcRequestBuilders.get("/actuator/health/readiness"))
        .andExpect(status().isServiceUnavailable())
        .andExpect(jsonPath("$.status").value("OUT_OF_SERVICE"));
  }

}
