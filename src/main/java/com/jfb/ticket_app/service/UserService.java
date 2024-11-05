package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.dao.UserDAO;
import com.jfb.ticket_app.util.interfaces.Identifiable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements Identifiable {

  private final String CLASS_ID = generateId();
  private final UserDAO userDAO;

  @Autowired
  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void saveUser(User user) {
    userDAO.saveUser(user);
  }

  public User getUserById(String id) {
    return userDAO.getUserById(id);
  }

  public void deleteUserAndTickets(String id) {
    userDAO.deleteUserAndTickets(id);
  }

  public void updateUserStatusAndCreateTicket(User user, TicketType ticketType) {
    userDAO.updateUserStatusAndCreateTicket(user, ticketType);
  }

  @Override
  public String getId() {
    return this.CLASS_ID;
  }
}
