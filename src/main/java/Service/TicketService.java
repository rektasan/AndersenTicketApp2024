package Service;

import Model.StadiumSectors;
import Model.Ticket;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TicketService {

  private Map<String, Ticket> tempTicketStorageMap;

  public TicketService(int numberOfTickets) {

    tempTicketStorageMap = new HashMap<>();

    for (int i = 0; i<numberOfTickets; i++) {
      Ticket ticket = new Ticket("Main Hall", 100, 1730000000, true, StadiumSectors.A, 50, new BigDecimal("69.99"));
      tempTicketStorageMap.put(ticket.getID(), ticket);
    }

  }

  public void printAllTickets() {
    tempTicketStorageMap.values().forEach(System.out::println);
  }

}
