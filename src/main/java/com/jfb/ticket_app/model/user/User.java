package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
@Entity
@Table(name = "users")
public class User implements Identifiable {

  @Id
  protected String id = generateId();

  @Column(name = "name")
  protected String name;

  @Column(name = "role")
  protected String role;

  @Column(name = "creation_date")
  protected Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
  private List<Ticket> tickets;

  public void printRole() {
    System.out.println("Role: " + role);
  };

  @Override
  public String getId() {
    return this.id;
  }

}
