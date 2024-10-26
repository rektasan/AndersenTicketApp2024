package com.jfb.ticket_app;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.TicketTypes;
import com.jfb.ticket_app.model.user.Admin;
import com.jfb.ticket_app.model.user.Client;
import com.jfb.ticket_app.repository.dao.TicketDaoImpl;
import com.jfb.ticket_app.repository.dao.UserDaoImpl;
import com.jfb.ticket_app.service.TicketService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    testDatabaseTask();
  }

  public static void testOOPTask() {
    TicketService eventTickets = new TicketService(10);
    Scanner scanner = new Scanner(System.in);

    System.out.println("The list of all tickets:");
    eventTickets.printAllTickets();

    System.out.print("Please enter the ticket id:");
    String inputID = scanner.next();
    Ticket targetTicket = eventTickets.getTicketById(inputID);

    if (targetTicket != null) {
      System.out.println("Ticket found: " + targetTicket);
      System.out.println("\nPolymorphism examples:");
      Admin admin = new Admin("Bruce");
      Client client = new Client("John");

      System.out.println(admin);
      admin.printRole();
      admin.checkTicket(targetTicket);

      System.out.println("\n" + client);
      client.printRole();
      client.getTicket(targetTicket);

      System.out.println();
      targetTicket.share("+333-333-3333");
      targetTicket.share("+333-333-3333", "johnSmith@gmail.com");
    } else {
      System.out.println("Ticket with id - " + inputID + " was not found.");
    }
    scanner.close();
  }

  public static void testCollectionTask() {

  }

  public static void testDatabaseTask() {
    UserDaoImpl userDao = new UserDaoImpl();
    Admin adminEntry = new Admin("Boss");
    Client clientEntry = new Client("Client");

    userDao.saveUser(adminEntry);
    userDao.saveUser(clientEntry);

    System.out.println(adminEntry + " " + clientEntry + " are saved in users table inside my_ticket_service_db");
    System.out.println("All entries in users table: \n" + userDao.selectAllUsers());

    System.out.println("Looking up the " + adminEntry + " by id. . .");
    System.out.println(userDao.fetchUserById(adminEntry.getId()));

    TicketDaoImpl ticketDao = new TicketDaoImpl();
    Ticket adminTicket = new Ticket(adminEntry.getId(), TicketTypes.YEAR, Timestamp.valueOf(LocalDateTime.now()));
    Ticket clientTicket = new Ticket(clientEntry.getId(), TicketTypes.YEAR, Timestamp.valueOf(LocalDateTime.now()));

    ticketDao.saveTicket(adminTicket);
    ticketDao.saveTicket(clientTicket);

    System.out.println(adminTicket + " " + clientTicket + " are saved in tickets table inside my_ticket_service_db");
    System.out.println("All entries in tickets table: \n" + userDao.selectAllUsers());

    System.out.println("Fetching tickets. . .");
    System.out.println(ticketDao.fetchTicketById(adminTicket.getId()) + "\n" +
        ticketDao.fetchTicketById(clientTicket.getId()) + "\n" +
        ticketDao.fetchTicketById(clientTicket.getId()) + "\n" +
        ticketDao.fetchTicketByUserId(clientEntry.getId()));

    ticketDao.updateTicketType(clientEntry.getId(), TicketTypes.MONTH);

    userDao.deleteUserAndTickets(adminEntry.getId());
    userDao.deleteUserAndTickets(clientEntry.getId());
  }

}
