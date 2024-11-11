package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;

public class Admin extends User {

  public void checkTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being checked by " + this);
  }

}
