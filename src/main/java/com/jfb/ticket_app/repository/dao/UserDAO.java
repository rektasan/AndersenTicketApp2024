package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.Status;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import com.jfb.ticket_app.repository.dao.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {

  @Value("${ableToUpdateAndCreate}")
  private String ableToUpdateAndCreate;

  private static final String INSERT_USER = "INSERT INTO users (id, name, role, status, creation_date) VALUES (?, ?, ?, CAST(? AS status), ?)";
  private static final String INSERT_TICKET = "INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, CAST(? AS ticket_type), ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
  private static final String DELETE_USER_AND_TICKETS = "DELETE FROM users WHERE id = ?";
  private static final String UPDATE_USER_STATUS_AND_CREATE_TICKET = "UPDATE users set status=CAST(? AS status) WHERE id=?";

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public void saveUser(User user) {
    jdbcTemplate.update(INSERT_USER, user.getId(), user.getName(), user.getRole(), user.getStatus().name(), user.getCreationTime());
  }

  @Transactional
  public User getUserById(String id) {
    return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
  }

  @Transactional
  public void deleteUserAndTickets(String id) {
    jdbcTemplate.update(DELETE_USER_AND_TICKETS, id);
  }

  @Transactional
  public void updateUserStatusAndCreateTicket(User user, TicketType ticketType) {
    if (ableToUpdateAndCreate.equalsIgnoreCase("ON")) {
      Ticket ticket = new Ticket(user.getId(), ticketType);
      user.addTicket(ticket);

      jdbcTemplate.update(INSERT_TICKET, ticket.getId(), ticket.getUserId(), ticket.getTicketType().name(), ticket.getCreationDate());
      jdbcTemplate.update(UPDATE_USER_STATUS_AND_CREATE_TICKET, Status.ACTIVATED.name(), user.getId());
    }
    else {
      throw new IllegalArgumentException("The functionality is currently disabled.");
    }
  }

}
