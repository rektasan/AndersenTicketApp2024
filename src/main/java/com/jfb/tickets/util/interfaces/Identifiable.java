package com.jfb.tickets.util.interfaces;

import java.util.UUID;

public interface Identifiable {

  int getClassId();

  default int generateClassId() {
    return Math.abs(UUID.randomUUID().toString().substring(0, 6).hashCode());
  }

}
