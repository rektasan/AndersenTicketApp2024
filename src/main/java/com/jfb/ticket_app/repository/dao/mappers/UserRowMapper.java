package com.jfb.ticket_app.repository.dao.mappers;

import com.jfb.ticket_app.model.user.Admin;
import com.jfb.ticket_app.model.user.Client;
import com.jfb.ticket_app.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    String role = resultSet.getString("role");
    if (role.equalsIgnoreCase("admin")) {
      Admin admin = new Admin();
      admin.setId(resultSet.getString("id"));
      admin.setName(resultSet.getString("name"));
      admin.setRole(role);
      return admin;
    } else if (role.equalsIgnoreCase("client")) {
      Client client = new Client();
      client.setId(resultSet.getString("id"));
      client.setName(resultSet.getString("name"));
      client.setRole(role);
      return client;
    } else {
      User user = new User();
      user.setId(resultSet.getString("id"));
      user.setName(resultSet.getString("name"));
      user.setRole(role);
      return user;
    }
  }
}
