package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.SessionFactoryProvider;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketDAO implements Identifiable {

  private String classId = generateId();

  public void saveTicket(Ticket ticket) {
    Transaction transaction = null;
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(ticket);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(e.getMessage());
    }
  }

  public Ticket getTicketById(String id) {
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      return session.get(Ticket.class, id);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  public List<Ticket> getTicketsByUserId(String userId) {
    List<Ticket> tickets = new ArrayList<>();
    Transaction transaction = null;
    try(Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      User user = session.get(User.class, userId);
      if (user.getTickets() != null) {
        tickets = user.getTickets();
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(e.getMessage());
      return null;
    }
    return tickets;
  }

  public void updateTicketType(String id, TicketType newType) {
    Transaction transaction = null;
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Ticket ticket = session.get(Ticket.class, id);
      if (ticket != null) {
        ticket.setTicketType(newType);
        session.update(ticket);
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(e.getMessage());
    }
  }

  @Override
  public String getId() {
    return this.classId;
  }
}
