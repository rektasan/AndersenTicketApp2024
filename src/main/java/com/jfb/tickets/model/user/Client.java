package com.jfb.tickets.model.user;

import com.jfb.tickets.model.ticket.Ticket;
import com.jfb.tickets.util.Constants;

import lombok.ToString;

@ToString
public class Client extends User {

  private final int CLASS_ID = generateClassId();

  public Client() {
    role = Constants.CLIENT_ROLE;
  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

  @Override
  public void printRole() {
    System.out.println("Role: " + role);
  }

  public void getTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being received by " + role);
  }

}
