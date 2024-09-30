package _2;

import java.math.BigDecimal;

public class TicketService {

  public static void main(String[] args) {

    Ticket emptyTicket = new Ticket();
    Ticket fullTicket = new Ticket("Main Hall", 100, 1827416155L, true, 'B', 5.5, 69.99d);
    Ticket limitedTicket = new Ticket("Main Hall", 101, 1827416155L);

    System.out.println("\nEmpty Ticket: " + emptyTicket + "\nFull Ticket: " + fullTicket + "\nLimited Ticket: " + limitedTicket);

    limitedTicket.setTicketPrice(BigDecimal.valueOf(99.69d));

    emptyTicket.printTicketInfo();
    fullTicket.printTicketInfo();
    limitedTicket.printTicketInfo();

    System.out.println("Attempting to instantiate an object of the Ticket class with an invalid timestamp. . .");
    Ticket invalidFullTicket = new Ticket("Main Hall", 100, 1627416155L, true, 'B', 5.5, 69.99d);
    invalidFullTicket.printTicketInfo();

  }
}
