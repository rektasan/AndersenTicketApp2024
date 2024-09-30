package _2;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Ticket {

  private static int lastTicketID = 0;
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

  private final String ID;
  private String concertHall;
  private int eventCode;
  private long eventTime;
  private boolean isPromo;
  private char stadiumSector;
  private double maxBackpackWeight;
  private final LocalDateTime creationTime;
  private BigDecimal ticketPrice;

  public Ticket(String concertHall, int eventCode, long time, boolean isPromo, char stadiumSector, double maxBackpackWeight,
      double ticketPrice) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);
    setStadiumSector(stadiumSector);
    setMaxBackpackWeight(maxBackpackWeight);
    setTicketPrice(ticketPrice);

    this.ID = generateNextTicketID();
    this.isPromo = isPromo;
    this.creationTime = LocalDateTime.now();

  }

  public Ticket(String concertHall, int eventCode, long time) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);

    this.ID = generateNextTicketID();
    this.creationTime = LocalDateTime.now();

  }

  public Ticket() {
    this.ID = generateNextTicketID();
    this.creationTime = LocalDateTime.now();
  }

  public String getID() {
    return ID;
  }

  public String getConcertHall() {
    return concertHall;
  }

  public void setConcertHall(String concertHall) {

    if (concertHall != null && concertHall.length() <= 10) {
      this.concertHall = concertHall;
    } else {
      throw new IllegalArgumentException("Invalid Concert Hall. Must not exceed 10 characters.");
    }

  }

  public int getEventCode() {
    return eventCode;
  }

  public void setEventCode(int eventCode) {

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

  public char getStadiumSector() {
    return stadiumSector;
  }

  public void setStadiumSector(char stadiumSector) {

    if (stadiumSector >= 'A' && stadiumSector <= 'C') {
      this.stadiumSector = stadiumSector;
    } else {
      throw new IllegalArgumentException("Invalid Stadium Sector. Must be A, B or C.");
    }

  }

  public double getMaxBackpackWeight() {
    return maxBackpackWeight;
  }

  public void setMaxBackpackWeight(double maxBackpackWeight) {

    if (maxBackpackWeight > 0) {
      this.maxBackpackWeight = maxBackpackWeight;
    } else {
      throw new IllegalArgumentException("Invalid Backpack Weight. Must be a positive value.");
    }

  }

  public BigDecimal getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(double ticketPrice) {

    if (ticketPrice >= 0) {
      this.ticketPrice = BigDecimal.valueOf(ticketPrice);
    } else {
      throw new IllegalArgumentException("Invalid Ticket Price. Must be a positive value.");
    }

  }

  public boolean isPromo() {
    return isPromo;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }


  private String generateNextTicketID() {
    lastTicketID++;
    return String.valueOf(lastTicketID);

  }

  public void printTicketInfo() {
    System.out.println("******************************\n" +
        "Ticket Info:" +
        "\n" + "Ticket ID: " + getID() +
        "\n" + "Concert Hall: " + getConcertHall() +
        "\n" + "Event Code: " + getEventCode() +
        "\n" + "Event Time: " + LocalDateTime.ofInstant(Instant.ofEpochSecond(getEventTime()), ZoneId.systemDefault()).format(FORMATTER) +
        "\n" + "Promo: " + (isPromo() ? "Yes" : "No") +
        "\n" + "Stadium Sector: " + getStadiumSector() +
        "\n" + "Max Backpack Weight: " + getMaxBackpackWeight() +
        "\n" + "Creation Time: " + getCreationTime().format(FORMATTER) +
        "\n" + "Price: $" + getTicketPrice() +
        "\n******************************");
  }

  @Override
  public String toString() {
    return "Ticket{" +
        "id=" + ID +
        ", concertHall=" + concertHall +
        ", eventCode=" + eventCode +
        ", eventTime=" + eventTime +
        ", isPromo=" + isPromo +
        ", stadiumSector=" + stadiumSector +
        ", maxBackpackWeight=" + maxBackpackWeight +
        ", creationTime=" + creationTime +
        ", ticketPrice=" + ticketPrice +
        '}';
  }
}
