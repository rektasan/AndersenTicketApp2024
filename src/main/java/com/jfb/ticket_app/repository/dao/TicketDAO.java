package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketTypes;

import com.jfb.ticket_app.repository.dao.mappers.TicketRowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAO {

  private static final String INSERT_TICKET = "INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, CAST(? AS ticket_type), ?)";
  private static final String SELECT_TICKET_BY_ID = "SELECT * FROM tickets WHERE id = ?";
  private static final String SELECT_TICKETS_BY_USER_ID = "SELECT * FROM tickets WHERE user_id = ?";
  private static final String UPDATE_TICKET_TYPE = "UPDATE tickets SET ticket_type = CAST(? AS ticket_type) WHERE id = ?";

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public TicketDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveTicket(Ticket ticket) {
    jdbcTemplate.update(INSERT_TICKET, ticket.getId(), ticket.getUserId(), ticket.getTicketType().name(), ticket.getCreationDate());
  }

  public Ticket getTicketById(String id) {
    return jdbcTemplate.queryForObject(SELECT_TICKET_BY_ID, new TicketRowMapper(), id);
  }

  public List<Ticket> getTicketsByUserId(String userId) {
    return jdbcTemplate.query(SELECT_TICKETS_BY_USER_ID, new TicketRowMapper(), userId);
  }

  public void updateTicketType(String id, TicketTypes newType) {
    jdbcTemplate.update(UPDATE_TICKET_TYPE, newType.name(), id);
  }
}