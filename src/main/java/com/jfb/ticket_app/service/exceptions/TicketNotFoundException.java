package com.jfb.ticket_app.service.exceptions;

public class TicketNotFoundException extends RuntimeException {

  public TicketNotFoundException(String message) {
    super(message);
  }

}
