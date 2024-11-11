package com.jfb.ticket_app.model.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.ticket.enums.StadiumSectors;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.util.TicketValidator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "ticket_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private TicketType ticketType;

  @Column(name = "creation_date")
  private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

  public Ticket(User user, TicketType type) {
    this.user = user;
    this.ticketType = type;
  }
}
