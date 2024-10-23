package com.jfb.ticket_app.util.interfaces;

import java.util.UUID;

public interface Identifiable {

  String getId();

  default String generateId() {
    return String.valueOf(Math.abs(UUID.randomUUID().toString().substring(0, 6).hashCode()));
  }

}
