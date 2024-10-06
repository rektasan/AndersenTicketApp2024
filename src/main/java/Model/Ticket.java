package Model;

import Interfaces.Identifiable;
import Interfaces.Printable;
import Model.enums.StadiumSectors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

public class Ticket implements Identifiable, Printable {

  private final String TICKET_ID = generateRandomTicketId();
  private final LocalDateTime CREATION_TIME = LocalDateTime.now();

  private int classId;
  private int eventCode;
  private long eventTime;
  private boolean isPromo;
  private double maxBackpackWeight;

  private String concertHall;
  private StadiumSectors stadiumSector;
  private BigDecimal ticketPrice;


  public Ticket(int classId, String concertHall, int eventCode, long time, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight,
      BigDecimal ticketPrice) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);
    setStadiumSector(stadiumSector);
    setMaxBackpackWeight(maxBackpackWeight);
    setTicketPrice(ticketPrice);

    this.classId = classId;
    this.isPromo = isPromo;

  }

  public Ticket(String concertHall, int eventCode, long time) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);

  }

  public Ticket() {
  }


  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }


  public String getTICKET_ID() {
    return TICKET_ID;
  }

  private String generateRandomTicketId() {
    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
  }


  public String getConcertHall() {
    return concertHall;
  }

  private void setConcertHall(String concertHall) {
    if (concertHall != null && concertHall.length() <= 10) {
      this.concertHall = concertHall;
    } else {
      throw new IllegalArgumentException("Invalid Concert Hall. Must not exceed 10 characters.");
    }
  }


  public int getEventCode() {
    return eventCode;
  }

  private void setEventCode(int eventCode) {
    if (eventCode >= 100 && eventCode <= 999) {
      this.eventCode = eventCode;
    } else {
      throw new IllegalArgumentException("Invalid Event Code. Must use a 3 digit number.");
    }
  }


  public long getEventTime() {
    return eventTime;
  }

  public void setEventTime(long eventTime) {
    long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

    if (eventTime >= currentTime) {
      this.eventTime = eventTime;
    } else {
      throw new IllegalArgumentException("Invalid Timestamp. Must not be in the past.");
    }
  }


  public StadiumSectors getStadiumSector() {
    return stadiumSector;
  }

  public void setStadiumSector(StadiumSectors stadiumSector) {
    this.stadiumSector = stadiumSector;
  }


  public double getMaxBackpackWeight() {
    return maxBackpackWeight;
  }

  private void setMaxBackpackWeight(double maxBackpackWeight) {
    if (maxBackpackWeight > 0) {
      this.maxBackpackWeight = maxBackpackWeight;
    } else {
      throw new IllegalArgumentException("Invalid Backpack Weight. Must be a positive value.");
    }
  }


  public BigDecimal getTicketPrice() {
    return ticketPrice;
  }

  private void setTicketPrice(BigDecimal ticketPrice) {

    if (ticketPrice.compareTo(BigDecimal.ZERO) >= 0) {
      this.ticketPrice = ticketPrice;
    } else {
      throw new IllegalArgumentException("Invalid Model.Ticket Price. Must be a positive value.");
    }

  }


  public boolean isPromo() {
    return isPromo;
  }

  public LocalDateTime getCREATION_TIME() {
    return CREATION_TIME;
  }


  public void share(String phone) {
    System.out.println("Ticket is shared by phone: " + phone);
  }

  public void share(String phone, String email) {
    System.out.println("Ticket is shared by phone: " + phone + ", and by email: " + email);
  }


  @Override
  public void print() {
    System.out.println("Ticket's print() method : " + this);
  }

  @Override
  public String toString() {
    return "Ticket{" +
        "classId=" + classId +
        ",TICKET_ID=" + TICKET_ID +
        ", concertHall=" + concertHall +
        ", eventCode=" + eventCode +
        ", eventTime=" + eventTime +
        ", isPromo=" + isPromo +
        ", stadiumSector=" + stadiumSector +
        ", maxBackpackWeight=" + maxBackpackWeight +
        ", CREATION_TIME=" + CREATION_TIME +
        ", ticketPrice=" + ticketPrice +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Ticket ticket)) {
      return false;
    }
    return classId == ticket.classId && eventCode == ticket.eventCode && eventTime == ticket.eventTime && isPromo == ticket.isPromo
        && Double.compare(maxBackpackWeight, ticket.maxBackpackWeight) == 0 && Objects.equals(TICKET_ID, ticket.TICKET_ID)
        && Objects.equals(CREATION_TIME, ticket.CREATION_TIME) && Objects.equals(concertHall, ticket.concertHall)
        && stadiumSector == ticket.stadiumSector && Objects.equals(ticketPrice, ticket.ticketPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TICKET_ID, CREATION_TIME, classId, eventCode, eventTime, isPromo, maxBackpackWeight, concertHall, stadiumSector, ticketPrice);
  }
}
