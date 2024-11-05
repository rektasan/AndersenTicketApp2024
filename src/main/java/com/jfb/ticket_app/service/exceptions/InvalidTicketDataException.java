package com.jfb.ticket_app.service.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class InvalidTicketDataException extends JsonProcessingException {

  public InvalidTicketDataException(String message, Throwable cause) {
    super(message, cause);
  }

}
