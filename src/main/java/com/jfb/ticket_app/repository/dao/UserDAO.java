package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.user.User;

import org.springframework.jdbc.core.JdbcTemplate;
import com.jfb.ticket_app.repository.dao.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

  private static final String INSERT_USER = "INSERT INTO users (id, name, role, creation_date) VALUES (?, ?, ?, ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
  private static final String DELETE_USER_AND_TICKETS = "DELETE FROM users WHERE id = ?";

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveUser(User user) {
    jdbcTemplate.update(INSERT_USER, user.getId(), user.getName(), user.getRole(), user.getCreationTime());
  }

  public User getUserById(String id) {
    return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
  }

  public void deleteUserAndTickets(String id) {
    jdbcTemplate.update(DELETE_USER_AND_TICKETS, id);
  }

}
