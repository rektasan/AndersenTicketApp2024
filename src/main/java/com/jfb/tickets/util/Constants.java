package com.jfb.tickets.util;

import com.jfb.tickets.util.interfaces.Identifiable;

public class Constants implements Identifiable {

  private final int CLASS_ID = generateClassId();

  public static final String INVALID_TICKET = "Error instantiating a ticket: ";
  public static final String INVALID_CONCERT_HALL = "Invalid Concert Hall. Must not exceed 10 characters.";
  public static final String INVALID_EVENT_CODE = "Invalid Event Code. Must use a 3 digit number.";
  public static final String INVALID_EVENT_TIME = "Invalid Event Time. Must not be in the past.";
  public static final String INVALID_BP_WEIGHT = "Invalid Backpack Weight. Must be a positive value.";
  public static final String INVALID_TICKET_PRICE = "Invalid Ticket Price. Must be a positive value.";

  public static final String CLIENT_ROLE = "client";
  public static final String ADMIN_ROLE = "admin";

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

}
