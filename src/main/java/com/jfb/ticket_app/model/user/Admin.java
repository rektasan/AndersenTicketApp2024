package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.util.Constants;

import java.sql.Timestamp;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Admin extends User {

  public Admin(String id, String name, Timestamp timestamp) {
    this.id = id;
    this.name = name;
    this.creationTime = timestamp;
    this.role = Constants.ADMIN_ROLE;
  }

  public Admin(String name) {
    this.name = name;
    this.role = Constants.ADMIN_ROLE;
  }

  public void checkTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being checked by " + role);
  }
}
