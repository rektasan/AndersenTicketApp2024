package com.jfb.ticket_app.model.ticket;

import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.util.Constants;
import com.jfb.ticket_app.util.TicketValidator;
import com.jfb.ticket_app.util.annotations.AnnotationProcessor;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import com.jfb.ticket_app.util.interfaces.Printable;
import com.jfb.ticket_app.model.ticket.enums.StadiumSector;
import com.jfb.ticket_app.util.annotations.NullableWarning;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "tickets")
public class Ticket implements Identifiable, Printable {

  @Id
  private String id = generateId();

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "ticket_type", nullable = false)
  private TicketType ticketType;

  @Column(name = "creation_date")
  private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

  @Transient
  private String concertHall;

  @Transient
  private int eventCode;

  @Setter
  @Transient
  private LocalDateTime eventTime;

  @Transient
  private boolean isPromo;

  @Setter
  @Transient
  private StadiumSector stadiumSector;

  @Transient
  private double maxBackpackWeight;

  @NullableWarning
  @Transient
  private BigDecimal ticketPrice;

  public Ticket(TicketType ticketType, String concertHall, int eventCode, LocalDateTime time, boolean isPromo, StadiumSector stadiumSector,
      double maxBackpackWeight, BigDecimal ticketPrice) {
    try {
      TicketValidator.validateConcertHall(concertHall);
      TicketValidator.validateEventCode(eventCode);
      TicketValidator.validateEventTime(time);
      TicketValidator.validateMaxBackpackWeight(maxBackpackWeight);
      TicketValidator.validateTicketPrice(ticketPrice);

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


  public Ticket(User user, TicketType ticketType, Timestamp creationDate) {
    this.user = user;
    this.ticketType = ticketType;
    this.creationDate = creationDate;
  }

  public Ticket(String id, User user, TicketType ticketType, Timestamp creationDate) {
    this.id = id;
    this.user = user;
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
