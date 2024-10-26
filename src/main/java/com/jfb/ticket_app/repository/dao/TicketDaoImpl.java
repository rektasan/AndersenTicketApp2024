package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketTypes;
import com.jfb.ticket_app.repository.DatabaseManager;
import com.jfb.ticket_app.util.storage.DisarrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDaoImpl {

  private static final String INSERT_TICKET = "INSERT INTO tickets (id, user_id, ticket_type) VALUES (?, ?, ?)";
  private static final String SELECT_TICKET_BY_ID = "SELECT * FROM tickets WHERE id = ?";
  private static final String SELECT_TICKET_BY_USER_ID = "SELECT * FROM tickets WHERE user_id = ?";
  private static final String UPDATE_TICKET_TYPE = "UPDATE tickets SET ticket_type = ? WHERE id = ?";
  private static final String SELECT_ALL_TICKETS = "SELECT * FROM tickets";

  public void saveTicket(Ticket o) {
    try (Connection connection = DatabaseManager.connect(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET)) {
      preparedStatement.setString(1, o.getId());
      preparedStatement.setString(2, o.getUserId());
      preparedStatement.setString(3, o.getTicketType().toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public Ticket fetchTicketById(String id) {
    try (Connection connection = DatabaseManager.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_ID)) {
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new Ticket(resultSet.getString("id"), resultSet.getString("user_id"),
          TicketTypes.valueOf(resultSet.getString("ticket_type")), resultSet.getTimestamp("creation_date"));
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public Ticket fetchTicketByUserId(String userId) {
    try (Connection connection = DatabaseManager.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_USER_ID)) {
      preparedStatement.setString(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new Ticket(resultSet.getString("id"), resultSet.getString("user_id"),
          TicketTypes.valueOf(resultSet.getString("ticket_type")), resultSet.getTimestamp("creation_date"));
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public void updateTicketType(String id, TicketTypes newType) {
    try (Connection connection = DatabaseManager.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET_TYPE)) {

      preparedStatement.setString(1, newType.name());
      preparedStatement.setString(2, id);
      int rowsAffected = preparedStatement.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("Ticket type updated successfully for ticket ID: " + id);
      } else {
        System.out.println("No ticket found with ID: " + id);
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public DisarrayList<Ticket> selectAllTickets() {
    DisarrayList<Ticket> tickets = new DisarrayList<>();
    try (Connection connection = DatabaseManager.connect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        tickets.put(new Ticket(resultSet.getString("id"),
            resultSet.getString("user_id"),
            TicketTypes.valueOf(resultSet.getString("ticket_type")),
            resultSet.getTimestamp("creation_date")));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return tickets;
  }
}
