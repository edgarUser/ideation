package org.ideation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SystemDetails {
  private int availableProcessors;
  private long maxMemory;
  private long totalMemory;
  private long freeMemory;
}
