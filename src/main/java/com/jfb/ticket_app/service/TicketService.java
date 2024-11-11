package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.repository.TicketRepository;
import com.jfb.ticket_app.service.exceptions.TicketNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

  private final TicketRepository ticketRepository;

  public void saveTicket(Ticket ticket) {
    ticketRepository.save(ticket);
  }

  public Ticket getTicketById(UUID id) {
    Optional<Ticket> ticket = ticketRepository.findById(id);
    return ticket.orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
  }

  public List<Ticket> getTicketsByUserId(UUID userId) {
    return ticketRepository.findTicketsByUserId(userId);
  }

  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  public List<Ticket> getTicketsBeforeDate(Date beforeDate) {
    return ticketRepository.findTicketsBeforeDate(beforeDate);
  }

  public Ticket updateTicket(UUID id, Ticket updatedTicket) {
    Optional<Ticket> ticket = ticketRepository.findById(id);

    if (ticket.isPresent()) {
      Ticket newTicket = ticket.get();
      newTicket.setTicketType(updatedTicket.getTicketType());
      newTicket.setUser(updatedTicket.getUser());
      return ticketRepository.save(newTicket);
    } else {
      throw new IllegalArgumentException("Ticket with ID " + id + " not found.");
    }
  }

  public void deleteTicket(UUID id) {
    ticketRepository.deleteById(id);
  }
}
