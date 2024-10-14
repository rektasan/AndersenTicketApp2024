package com.jfb.tickets.service;

import com.jfb.tickets.model.ticket.Ticket;
import com.jfb.tickets.util.interfaces.Identifiable;
import com.jfb.tickets.util.storage.DisarrayList;

import java.time.LocalDateTime;

public class TicketService implements Identifiable {

  private final int CLASS_ID = generateClassId();
  private DisarrayList<Ticket> tempTicketStorage;

  public TicketService(int numberOfTickets) {

    tempTicketStorage = new DisarrayList<>();

    for (int i = 0; i < numberOfTickets; i++) {
      Ticket ticket = new Ticket("Main", 101, LocalDateTime.of(2024, 12, 31, 0, 0));
      tempTicketStorage.put(ticket);
    }

  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

  public Ticket getTicketById(String id) {
    for (int i = 0; i < tempTicketStorage.getSize(); i++) {
      Ticket tempTicket = tempTicketStorage.get(i);
      if (tempTicket.getTICKET_ID().equals(id)) {
        return tempTicket;
      }
    }
    return null;
  }

  public void printAllTickets() {
    for (int i = 0; i < tempTicketStorage.getSize(); i++) {
      Ticket tempTicket = tempTicketStorage.get(i);
      System.out.println(tempTicket);
    }
  }
}
