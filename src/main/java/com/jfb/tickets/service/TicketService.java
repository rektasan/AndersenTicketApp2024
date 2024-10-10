package com.jfb.tickets.service;

import com.jfb.tickets.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TicketService {

  private Map<String, Ticket> tempTicketStorageMap;

  public TicketService(int numberOfTickets) {

    tempTicketStorageMap = new HashMap<>();

    for (int i = 0; i < numberOfTickets; i++) {
      Ticket ticket = new Ticket("Main", 101, LocalDateTime.of(2024, 12, 31, 0, 0));
      tempTicketStorageMap.put(ticket.getTICKET_ID(), ticket);
    }

  }

  public Ticket getTicketById(String id) {
    return tempTicketStorageMap.get(id);
  }

  public void printAllTickets() {
    tempTicketStorageMap.values().forEach(System.out::println);
  }

}
