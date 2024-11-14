package com.jfb.ticket_app.model.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jfb.ticket_app.model.ticket.enums.TicketType;
import com.jfb.ticket_app.model.user.User;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

  public Ticket(User user, TicketType ticketType) {
    this.user = user;
    this.ticketType = ticketType;
  }

}
