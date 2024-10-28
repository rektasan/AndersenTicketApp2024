package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.SessionFactoryProvider;

import com.jfb.ticket_app.util.interfaces.Identifiable;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO implements Identifiable {

  private String classId = getId();

  public void saveUser(User user) {
    Transaction transaction = null;
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(user);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(e.getMessage());
    }
  }

  public User getUserById(String id) {
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      return session.get(User.class, id);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  public void deleteUserAndTickets(User user) {
    Transaction transaction = null;
    try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.delete(user);
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
