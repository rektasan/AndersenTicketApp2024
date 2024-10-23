package com.jfb.ticket_app.repository.dao;

import com.jfb.ticket_app.model.user.Admin;
import com.jfb.ticket_app.model.user.Client;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.repository.DatabaseManager;
import com.jfb.ticket_app.repository.GeneralDao;
import com.jfb.ticket_app.util.storage.DisarrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserDaoImpl implements GeneralDao<User> {

  private static final String INSERT_USER = "INSERT INTO users (id, name, role) VALUES (?, ?, ?)";
  private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
  private static final String DELETE_USER_AND_TICKETS = "DELETE FROM users WHERE id = ?";
  private static final String SELECT_ALL_USERS = "SELECT * FROM users";

  @Override
  public void save(User o) {
    try (Connection connection = DatabaseManager.connect(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
      preparedStatement.setString(1, o.getId());
      preparedStatement.setString(2, o.getName());
      preparedStatement.setString(3, o.getRole());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public User fetchById(String id) {
    try (Connection connection = DatabaseManager.connect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String userId = resultSet.getString("id");
        String name = resultSet.getString("name");
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        String userType = resultSet.getString("role");
        if ("client".equalsIgnoreCase(userType)) {
          return new Client(userId, name, creationDate);
        } else if ("admin".equalsIgnoreCase(userType)) {
          return new Admin(userId, name, creationDate);
        } else {
          System.err.println("Unknown user type.");
        }
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public void deleteUserAndTickets(String id) {
    try (Connection connection = DatabaseManager.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_AND_TICKETS)) {

      preparedStatement.setString(1, id);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public DisarrayList<User> selectAll() {
    DisarrayList<User> users = new DisarrayList<>();
    try (Connection connection = DatabaseManager.connect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.put(new User(resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("role"),
            resultSet.getTimestamp("creation_date")));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return users;
  }
}
