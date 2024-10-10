package com.jfb.tickets.model.user;

import com.jfb.tickets.model.ticket.Ticket;
import lombok.ToString;

@ToString
public class Client extends User{

  public Client(int classId) {
    this.classId = classId;
    role = "client";
  }

  public void getTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being received by " + role);
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }

  @Override
  public void printRole() {
    System.out.println("Role: " + role);
  }
}
