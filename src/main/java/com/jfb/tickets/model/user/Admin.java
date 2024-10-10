package com.jfb.tickets.model.user;

import com.jfb.tickets.model.ticket.Ticket;
import lombok.ToString;

@ToString
public class Admin extends User {

  public Admin(int classId) {
    this.classId = classId;
    role = "admin";
  }

  public void checkTicket(Ticket ticket) {
    System.out.println(ticket + " \nis being checked by " + role);
  }

  @Override
  public void printRole() {
    System.out.println("Role: " + role);
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }
}
