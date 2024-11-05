package com.jfb.ticket_app;

import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.config.AppConfig;
import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.user.Admin;
import com.jfb.ticket_app.model.user.Client;
import com.jfb.ticket_app.service.BusTicketFileReader;
import com.jfb.ticket_app.service.TicketService;
import com.jfb.ticket_app.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) throws IOException {
    testDatabaseAndSpringTasks();
    testBusTicketReader();
  }

  public static void testOOPTask() {
    System.err.println("Method is not implemented. Outdated code.");
    /*
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
     */
  }

  public static void testDatabaseAndSpringTasks() {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);
    Admin adminEntry = new Admin("Boss");
    Client clientEntry = new Client("Client");
    Client clientJohn = new Client("John");

    userService.saveUser(adminEntry);
    userService.saveUser(clientEntry);
    userService.saveUser(clientJohn);

    userService.updateUserStatusAndCreateTicket(clientJohn, TicketType.YEAR);

    System.out.println(adminEntry + " " + clientEntry + " are saved in users table inside my_ticket_service_db");

    System.out.println("Looking up the " + adminEntry + " by id. . .");
    System.out.println(userService.getUserById(adminEntry.getId()));

    TicketService ticketService = context.getBean(TicketService.class);
    Ticket adminTicket = new Ticket(adminEntry.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now()));
    Ticket clientTicket = new Ticket(clientEntry.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now()));

    ticketService.saveTicket(adminTicket);
    ticketService.saveTicket(clientTicket);

    System.out.println(adminTicket + " " + clientTicket + " are saved in tickets table inside my_ticket_service_db");

    System.out.println("Fetching tickets. . .");
    System.out.println(ticketService.getTicketById(adminTicket.getId()) + "\n" +
        ticketService.getTicketById(clientTicket.getId()) + "\n" +
        ticketService.getTicketById(clientTicket.getId()) + "\n" +
        ticketService.getTicketsByUserId(clientEntry.getId()));

    ticketService.updateTicketType(clientEntry.getId(), TicketType.MONTH);

    userService.deleteUserAndTickets(adminEntry.getId());
    userService.deleteUserAndTickets(clientEntry.getId());
    userService.deleteUserAndTickets(clientJohn.getId());
  }

  public static void testBusTicketReader() throws IOException {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    BusTicketFileReader busTicketFileReader = context.getBean(BusTicketFileReader.class);

    System.out.println(busTicketFileReader.getBusTickets());
  }

}
