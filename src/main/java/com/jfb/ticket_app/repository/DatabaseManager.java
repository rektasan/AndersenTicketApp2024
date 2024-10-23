package com.jfb.ticket_app.repository;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

  private static final Dotenv DOTENV = Dotenv.load();

  private static final String URL = DOTENV.get("DB_URL");
  private static final String USER = DOTENV.get("DB_USER");
  private static final String PASSWORD = DOTENV.get("DB_PASSWORD");

  public static Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
