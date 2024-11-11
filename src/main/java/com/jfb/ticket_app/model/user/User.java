package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.ticket.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  @Column(name = "name")
  protected String name;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  protected Status status = Status.DEACTIVATED;

  @Column(name = "creation_date", nullable = false)
  protected Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  @Cascade(CascadeType.ALL)
  protected List<Ticket> userTickets = new ArrayList<>();

  public void printRole() {
    System.out.println("Role: " + this.getClass().getSimpleName());
  }

}
