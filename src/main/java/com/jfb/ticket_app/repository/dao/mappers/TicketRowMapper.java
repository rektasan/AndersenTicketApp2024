package com.jfb.ticket_app.repository.dao.mappers;

import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.ticket.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TicketRowMapper implements RowMapper<Ticket> {

  @Override
  public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Ticket ticket = new Ticket();
    ticket.setId(resultSet.getString("id"));
    ticket.setUserId(resultSet.getString("user_id"));
    ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
    ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
    return ticket;
  }
}
