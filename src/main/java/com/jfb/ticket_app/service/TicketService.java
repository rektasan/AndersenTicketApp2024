package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.repository.dao.TicketDAO;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TicketService implements Identifiable {

  private final TicketDAO ticketDAO = new TicketDAO();

  private String classId = generateId();

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
    return this.classId;
  }
}
