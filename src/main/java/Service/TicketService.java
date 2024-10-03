package Service;

import Model.StadiumSectors;
import Model.Ticket;
import java.math.BigDecimal;

public class TicketService {

  public static void main(String[] args) {

    Ticket ticketEmpty = new Ticket();
    Ticket ticketLimited = new Ticket("Main", 100, 1747943141);
    Ticket ticketFull = new Ticket("Main", 101, 1747943141, true, StadiumSectors.A, 50, new BigDecimal("69.99") );

    System.out.println(ticketEmpty + "\n" + ticketLimited + "\n" + ticketFull);
  }


}
