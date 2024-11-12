package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;

public class Client extends User {

  public void getTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being received by " + this);
  }

}
