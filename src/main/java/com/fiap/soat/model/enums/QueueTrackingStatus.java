package com.fiap.soat.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum QueueTrackingStatus {
  RECEIVED,
  IN_PREPARATION,
  READY,
  FINISHED;

  public static QueueTrackingStatus getByName(String name) {
    return Arrays.stream(QueueTrackingStatus.values())
        .filter(p -> p.name().equals(name))
        .findFirst()
        .orElse(null);
  }
}
