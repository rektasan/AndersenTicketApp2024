package com.jfb.ticket_app.model.user;

import com.jfb.ticket_app.util.interfaces.Identifiable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class User implements Identifiable {

  protected String id = generateId();
  protected String name;
  protected String role;
  protected Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());

  public User(String name, String role) {
    this.name = name;
    this.role = role;
  }

  public void printRole() {
    System.out.println("Role: " + role);
  };

  @Override
  public String getId() {
    return this.id;
  }

}
