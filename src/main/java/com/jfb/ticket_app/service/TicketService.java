package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.repository.dao.TicketDAO;
import com.jfb.ticket_app.util.interfaces.Identifiable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements Identifiable {

  private final String CLASS_ID = generateId();
  private final TicketDAO ticketDAO;

  @Autowired
  public TicketService(TicketDAO ticketDAO) {
    this.ticketDAO = ticketDAO;
  }

  public void saveTicket(Ticket ticket) {
    ticketDAO.saveTicket(ticket);
  }

  public Ticket getTicketById(String id) {
    return ticketDAO.getTicketById(id);
  }

  public List<Ticket> getTicketsByUserId(String userId) {
    return ticketDAO.getTicketsByUserId(userId);
  }

  public void updateTicketType(String id, TicketType newType) {
    ticketDAO.updateTicketType(id, newType);
  }


  @Override
  public String getId() {
    return this.CLASS_ID;
  }

}
