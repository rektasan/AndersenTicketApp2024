package com.jfb.tickets.service;

import com.jfb.tickets.model.ticket.Ticket;

import com.jfb.tickets.util.interfaces.Identifiable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TicketService implements Identifiable {

  private final int CLASS_ID = generateClassId();
  private Map<String, Ticket> tempTicketStorageMap;

  public TicketService(int numberOfTickets) {

    tempTicketStorageMap = new HashMap<>();

    for (int i = 0; i < numberOfTickets; i++) {
      Ticket ticket = new Ticket("Main", 101, LocalDateTime.of(2024, 12, 31, 0, 0));
      tempTicketStorageMap.put(ticket.getTICKET_ID(), ticket);
    }

  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }

  public Ticket getTicketById(String id) {
    return tempTicketStorageMap.get(id);
  }

  public void printAllTickets() {
    tempTicketStorageMap.values().forEach(System.out::println);
  }
}
