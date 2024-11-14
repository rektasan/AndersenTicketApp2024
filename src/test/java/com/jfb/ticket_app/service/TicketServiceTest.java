package com.jfb.ticket_app.service;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.Status;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.TicketRepository;
import com.jfb.ticket_app.service.exceptions.TicketNotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.UUID;


class TicketServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @InjectMocks
  private TicketService ticketService;

  private Ticket testTicket;
  private User testUser;

  @BeforeEach
  void setUp() {
    testUser = new User("TestJohn", Status.ACTIVATED);
    testTicket = new Ticket(testUser, TicketType.DAY);

    testUser.setId(UUID.randomUUID());
    testUser.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));

    testTicket.setId(UUID.randomUUID());
    testTicket.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
  }


  @Test
  void saveTicket_positive() {
    ticketService.saveTicket(testTicket);
    verify(ticketRepository, times(1)).save(testTicket);
  }

  @Test
  void saveTicket_negative() {
    doThrow(new RuntimeException("Save failed")).when(ticketRepository).save(any(Ticket.class));
    assertThrows(RuntimeException.class, () -> ticketService.saveTicket(testTicket));
  }

  @Test
  void saveTicket_corner() {
    ticketService.saveTicket(null);
    verify(ticketRepository, times(1)).save(null);
  }


  @Test
  void getTicketById_positive() {
    when(ticketRepository.findById(testTicket.getId())).thenReturn(Optional.of(testTicket));
    Ticket result = ticketService.getTicketById(testTicket.getId());
    assertNotNull(result);
    assertEquals(testTicket, result);
  }

  @Test
  void getTicketById_negative() {
    when(ticketRepository.findById(testTicket.getId())).thenReturn(Optional.empty());
    assertThrows(TicketNotFoundException.class, () -> ticketService.getTicketById(testTicket.getId()));
  }

  @Test
  void getTicketById_corner() {
    assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketById(null));
  }


  @Test
  void getTicketsByUserId_positive() {
    List<Ticket> tickets = List.of(testTicket);
    when(ticketRepository.findTicketsByUserId(testUser.getId())).thenReturn(tickets);
    List<Ticket> result = ticketService.getTicketsByUserId(testUser.getId());
    assertEquals(tickets, result);
  }

  @Test
  void getTicketsByUserId_negative() {
    when(ticketRepository.findTicketsByUserId(any())).thenReturn(Collections.emptyList());
    List<Ticket> result = ticketService.getTicketsByUserId(UUID.randomUUID());
    assertTrue(result.isEmpty());
  }

  @Test
  void getTicketsByUserId_corner() {
    List<Ticket> result = ticketService.getTicketsByUserId(null);
    assertTrue(result.isEmpty());
  }


  @Test
  void getAllTickets_positive() {
    List<Ticket> tickets = List.of(testTicket);
    when(ticketRepository.findAll()).thenReturn(tickets);
    List<Ticket> result = ticketService.getAllTickets();
    assertEquals(tickets, result);
  }

  @Test
  void getAllTickets_negative() {
    when(ticketRepository.findAll()).thenReturn(Collections.emptyList());
    List<Ticket> result = ticketService.getAllTickets();
    assertTrue(result.isEmpty());
  }

  @Test
  void getAllTickets_corner() {
    when(ticketRepository.findAll()).thenReturn(null);
    List<Ticket> result = ticketService.getAllTickets();
    assertNull(result);
  }


  @Test
  void getTicketsBeforeDate_positive() {
    Date date = new Date();
    List<Ticket> tickets = List.of(testTicket);
    when(ticketRepository.findTicketsBeforeDate(date)).thenReturn(tickets);
    List<Ticket> result = ticketService.getTicketsBeforeDate(date);
    assertEquals(tickets, result);
  }

  @Test
  void getTicketsBeforeDate_negative() {
    Date date = new Date();
    when(ticketRepository.findTicketsBeforeDate(date)).thenReturn(Collections.emptyList());
    List<Ticket> result = ticketService.getTicketsBeforeDate(date);
    assertTrue(result.isEmpty());
  }

  @Test
  void getTicketsBeforeDate_corner() {
    List<Ticket> result = ticketService.getTicketsBeforeDate(null);
    assertTrue(result.isEmpty());
  }


  @Test
  void updateTicket_positive() {
    when(ticketRepository.findById(testTicket.getId())).thenReturn(Optional.of(testTicket));
    when(ticketRepository.save(any(Ticket.class))).thenReturn(testTicket);
    Ticket updatedTicket = new Ticket(testUser, TicketType.YEAR);
    Ticket result = ticketService.updateTicket(testTicket.getId(), updatedTicket);
    assertNotNull(result);
    assertEquals(testTicket, result);
  }

  @Test
  void updateTicket_negative() {
    when(ticketRepository.findById(testTicket.getId())).thenReturn(Optional.empty());
    Ticket updatedTicket = new Ticket(testUser, TicketType.YEAR);
    assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicket(testTicket.getId(), updatedTicket));
  }

  @Test
  void updateTicket_corner() {
    assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicket(null, testTicket));
  }


  @Test
  void deleteTicket_positive() {
    doNothing().when(ticketRepository).deleteById(testTicket.getId());
    ticketService.deleteTicket(testTicket.getId());
    verify(ticketRepository, times(1)).deleteById(testTicket.getId());
  }

  @Test
  void deleteTicket_negative() {
    doThrow(new RuntimeException("Delete failed")).when(ticketRepository).deleteById(testTicket.getId());
    assertThrows(RuntimeException.class, () -> ticketService.deleteTicket(testTicket.getId()));
  }

  @Test
  void deleteTicket_corner() {
    assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicket(null));
  }
}
