package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class Ticket {

  private final String ID = generateRandomID();
  private final LocalDateTime CREATION_TIME = LocalDateTime.now();

  private int eventCode;
  private long eventTime;
  private boolean isPromo;
  private double maxBackpackWeight;

  private String concertHall;
  private StadiumSectors stadiumSector;
  private BigDecimal ticketPrice;

  public Ticket(String concertHall, int eventCode, long time, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight,
      BigDecimal ticketPrice) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);
    setStadiumSector(stadiumSector);
    setMaxBackpackWeight(maxBackpackWeight);
    setTicketPrice(ticketPrice);

    this.isPromo = isPromo;

  }

  public Ticket(String concertHall, int eventCode, long time) {

    setConcertHall(concertHall);
    setEventCode(eventCode);
    setEventTime(time);

  }

  public Ticket() {  }


  public String getID() {
    return ID;
  }

  public String generateRandomID() {
    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
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


  public StadiumSectors getStadiumSector() {
    return stadiumSector;
  }

  public void setStadiumSector(StadiumSectors stadiumSector) {

    this.stadiumSector = stadiumSector;

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

  public void setTicketPrice(BigDecimal ticketPrice) {

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


  @Override
  public String toString() {
    return "Model.Ticket{" +
        "id=" + ID +
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
}
