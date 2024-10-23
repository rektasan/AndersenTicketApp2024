package com.jfb.ticket_app;

import com.jfb.ticket_app.model.user.Admin;
import com.jfb.ticket_app.model.user.Client;
import com.jfb.ticket_app.repository.dao.TicketDaoImpl;
import com.jfb.ticket_app.repository.dao.UserDaoImpl;

public class Main {

  public static void main(String[] args) {
    TicketDaoImpl ticketDao = new TicketDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();

    Admin adminEntry = new Admin("Boss");
    Client clientEntry = new Client("Client");

    userDao.save(adminEntry);
    userDao.save(clientEntry);
    System.out.println(adminEntry + " " + clientEntry + " are saved in users table inside my_ticket_service_db");

    System.out.println("All entries in users table: " + userDao.selectAll());
  }

}
