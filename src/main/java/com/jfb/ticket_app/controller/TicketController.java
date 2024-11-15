package com.jfb.ticket_app.controller;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.service.TicketService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketService ticketService;

  @GetMapping("/{id}")
  public Ticket getTicketById(@PathVariable("id") UUID id) {
    return ticketService.getTicketById(id);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteTicketById(@PathVariable("id") UUID id) {
    ticketService.deleteTicket(id);
  }

}
