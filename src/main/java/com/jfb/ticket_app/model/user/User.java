package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.Status;
import com.jfb.ticket_app.util.interfaces.Identifiable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Identifiable {

  protected String id = generateId();
  protected String name;
  protected String role;
  protected Status status = Status.DEACTIVATED;
  protected Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());
  protected List<Ticket> userTickets = new ArrayList<>();

  public User(String name, String role) {
    this.name = name;
    this.role = role;
  }


  public void addTicket(Ticket ticket) {
    userTickets.add(ticket);
    ticket.setUserId(this.id);
  }


  public void printRole() {
    System.out.println("Role: " + role);
  };

  @Override
  public String getId() {
    return this.id;
  }

}
