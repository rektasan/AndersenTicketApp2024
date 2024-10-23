package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.util.Constants;
import java.sql.Timestamp;

public class Client extends User {

  public Client(String id, String name, Timestamp timestamp) {
    this.id = id;
    this.name = name;
    this.creationTime = timestamp;
    this.role = Constants.CLIENT_ROLE;
  }

  public Client(String name) {
    this.name = name;
    this.role = Constants.CLIENT_ROLE;
  }

  public void getTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being received by " + role);
  }

}
