package com.jfb.tickets.model.user;

import com.jfb.tickets.model.ticket.Ticket;
import com.jfb.tickets.util.Constants;

import lombok.ToString;

@ToString
public class Admin extends User {

  private final int CLASS_ID = generateClassId();

  public Admin() {
    role = Constants.ADMIN_ROLE;
  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

  @Override
  public void printRole() {
    System.out.println("Role: " + role);
  }

  public void checkTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being checked by " + role);
  }
}
