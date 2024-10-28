package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.dao.UserDAO;
import com.jfb.ticket_app.util.interfaces.Identifiable;

public class UserService implements Identifiable {

  private final UserDAO userDAO = new UserDAO();

  private String classId = generateId();

  public void saveUser(User user) {
    userDAO.saveUser(user);
  }

  public User getUserById(String id) {
    return userDAO.getUserById(id);
  }

  public void deleteUserAndTickets(User user) {
    userDAO.deleteUserAndTickets(user);
  }

  @Override
  public String getId() {
    return this.classId;
  }
}
