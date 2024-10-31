package com.jfb.ticket_app.model.ticket;

import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.ticket.enums.StadiumSectors;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import com.jfb.ticket_app.util.interfaces.Printable;
import com.jfb.ticket_app.util.annotations.AnnotationProcessor;
import com.jfb.ticket_app.util.annotations.NullableWarning;
import com.jfb.ticket_app.util.TicketValidator;
import com.jfb.ticket_app.util.Constants;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket implements Identifiable, Printable {

  @Setter
  private String id = generateId();
  @Setter
  private String userId;
  @Setter
  private TicketType ticketType;
  @Setter
  private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

  private String concertHall;
  private int eventCode;
  @Setter
  private LocalDateTime eventTime;
  private boolean isPromo;
  @Setter
  private StadiumSectors stadiumSector;
  private double maxBackpackWeight;
  @NullableWarning
  private BigDecimal ticketPrice;

  public Ticket(String userId, TicketType ticketType, String concertHall, int eventCode, LocalDateTime time, boolean isPromo, StadiumSectors stadiumSector,
      double maxBackpackWeight, BigDecimal ticketPrice) {
    try {
      TicketValidator.validateConcertHall(concertHall);
      TicketValidator.validateEventCode(eventCode);
      TicketValidator.validateEventTime(time);
      TicketValidator.validateMaxBackpackWeight(maxBackpackWeight);
      TicketValidator.validateTicketPrice(ticketPrice);

      this.userId = userId;
      this.ticketType = ticketType;
      this.concertHall = concertHall;
      this.eventCode = eventCode;
      this.eventTime = time;
      this.isPromo = isPromo;
      this.stadiumSector = stadiumSector;
      this.maxBackpackWeight = maxBackpackWeight;
      this.ticketPrice = ticketPrice;

      AnnotationProcessor.processAnnotations(this);
    } catch (IllegalArgumentException e) {
      System.err.println(Constants.INVALID_TICKET + e.getMessage());
    }
  }

  public Ticket(String userId, String concertHall, int eventCode, LocalDateTime time) {
    try {
      TicketValidator.validateConcertHall(concertHall);
      TicketValidator.validateEventCode(eventCode);
      TicketValidator.validateEventTime(time);

      this.userId = userId;
      this.concertHall = concertHall;
      this.eventCode = eventCode;
      this.eventTime = time;

      AnnotationProcessor.processAnnotations(this);
    } catch (IllegalArgumentException e) {
      System.err.println(Constants.INVALID_TICKET + e.getMessage());
    }
  }


  public Ticket(String userId, TicketType ticketType, Timestamp creationDate) {
    this.userId = userId;
    this.ticketType = ticketType;
    this.creationDate = creationDate;
  }

  public Ticket(String id, String userId, TicketType ticketType, Timestamp creationDate) {
    this.id = id;
    this.userId = userId;
    this.ticketType = ticketType;
    this.creationDate = creationDate;
  }


  @Override
  public String generateId() {
    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
  }
  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void print() {
    System.out.println("Ticket's print() method : " + this);
  }

  public void share(String phone) {
    System.out.println(this.getId() + " Ticket is shared by phone: " + phone);
  }

  public void share(String phone, String email) {
    System.out.println(this.getId() + " Ticket is shared by phone: " + phone + ", and by email: " + email);
  }

}
