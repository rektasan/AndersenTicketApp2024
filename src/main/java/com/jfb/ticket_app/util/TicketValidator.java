package com.jfb.ticket_app.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketValidator {

  private static final String INVALID_CONCERT_HALL = "Invalid Concert Hall. Must not exceed 10 characters.";
  private static final String INVALID_EVENT_CODE = "Invalid Event Code. Must use a 3 digit number.";
  private static final String INVALID_EVENT_TIME = "Invalid Event Time. Must not be in the past.";
  private static final String INVALID_BP_WEIGHT = "Invalid Backpack Weight. Must be a positive value.";
  private static final String INVALID_TICKET_PRICE = "Invalid Ticket Price. Must be a positive value.";

  public static void validateConcertHall(String concertHall) {
    if (concertHall == null || concertHall.length() > 10) {
      throw new IllegalArgumentException(INVALID_CONCERT_HALL);
    }
  }

  public static void validateEventCode(int eventCode) {
    if (eventCode < 100 || eventCode > 999) {
      throw new IllegalArgumentException(INVALID_EVENT_CODE);
    }
  }

  public static void validateEventTime(LocalDateTime eventTime) {
    if (eventTime.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException(INVALID_EVENT_TIME);
    }
  }

  public static void validateMaxBackpackWeight(double maxBackpackWeight) {
    if (maxBackpackWeight <= 0) {
      throw new IllegalArgumentException(INVALID_BP_WEIGHT);
    }
  }

  public static void validateTicketPrice(BigDecimal ticketPrice) {
    if (ticketPrice.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(INVALID_TICKET_PRICE);
    }
  }
}
