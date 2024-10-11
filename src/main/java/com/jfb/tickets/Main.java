package com.jfb.tickets;

import com.jfb.tickets.model.user.Admin;
import com.jfb.tickets.model.user.Client;
import com.jfb.tickets.model.ticket.Ticket;
import com.jfb.tickets.service.TicketService;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

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
      Admin admin = new Admin();
      Client client = new Client();

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

}
