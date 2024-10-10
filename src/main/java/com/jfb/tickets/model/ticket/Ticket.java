package com.jfb.tickets.model.ticket;

import com.jfb.tickets.util.Constants;
import com.jfb.tickets.util.TicketValidator;
import com.jfb.tickets.util.annotations.AnnotationProcessor;
import com.jfb.tickets.util.annotations.NullableWarning;
import com.jfb.tickets.util.interfaces.Identifiable;
import com.jfb.tickets.util.interfaces.Printable;
import com.jfb.tickets.model.ticket.enums.StadiumSectors;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket implements Identifiable, Printable {

  private final String TICKET_ID = generateRandomTicketId();
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
  private final LocalDateTime CREATION_TIME = LocalDateTime.now();
  private int classId;

  public Ticket(String concertHall, int eventCode, LocalDateTime time, boolean isPromo, StadiumSectors stadiumSector,
      double maxBackpackWeight, BigDecimal ticketPrice, int classId) {
    try {
      TicketValidator.validateConcertHall(concertHall);
      TicketValidator.validateEventCode(eventCode);
      TicketValidator.validateEventTime(time);
      TicketValidator.validateMaxBackpackWeight(maxBackpackWeight);
      TicketValidator.validateTicketPrice(ticketPrice);

      this.classId = classId;
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

  public Ticket(String concertHall, int eventCode, LocalDateTime time) {
    try {
      TicketValidator.validateConcertHall(concertHall);
      TicketValidator.validateEventCode(eventCode);
      TicketValidator.validateEventTime(time);

      this.concertHall = concertHall;
      this.eventCode = eventCode;
      this.eventTime = time;

      AnnotationProcessor.processAnnotations(this);
    } catch (IllegalArgumentException e) {
      System.err.println(Constants.INVALID_TICKET + e.getMessage());
    }
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }

  @Override
  public void print() {
    System.out.println("Ticket's print() method : " + this);
  }


  private String generateRandomTicketId() {
    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
  }

  public void share(String phone) {
    System.out.println(this.getTICKET_ID() + " Ticket is shared by phone: " + phone);
  }

  public void share(String phone, String email) {
    System.out.println(this.getTICKET_ID() + " Ticket is shared by phone: " + phone + ", and by email: " + email);
  }

}
