package com.jfb.tickets.util;

import com.jfb.tickets.util.interfaces.Identifiable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketValidator implements Identifiable {

  private final int CLASS_ID = generateClassId();

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

  public static void validateConcertHall(String concertHall) {
    if (concertHall == null || concertHall.length() > 10) {
      throw new IllegalArgumentException(Constants.INVALID_CONCERT_HALL);
    }
  }

  public static void validateEventCode(int eventCode) {
    if (eventCode < 100 || eventCode > 999) {
      throw new IllegalArgumentException(Constants.INVALID_EVENT_CODE);
    }
  }

  public static void validateEventTime(LocalDateTime eventTime) {
    if (eventTime.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException(Constants.INVALID_EVENT_TIME);
    }
  }

  public static void validateMaxBackpackWeight(double maxBackpackWeight) {
    if (maxBackpackWeight <= 0) {
      throw new IllegalArgumentException(Constants.INVALID_BP_WEIGHT);
    }
  }

  public static void validateTicketPrice(BigDecimal ticketPrice) {
    if (ticketPrice.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(Constants.INVALID_TICKET_PRICE);
    }
  }
}
