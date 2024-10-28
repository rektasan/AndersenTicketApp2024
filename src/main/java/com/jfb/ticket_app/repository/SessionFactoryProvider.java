package com.jfb.ticket_app.repository;

import com.jfb.ticket_app.model.ticket.Ticket;
import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.util.interfaces.Identifiable;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SessionFactoryProvider implements Identifiable {

  private final String classId = generateId();
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class).addAnnotatedClass(Ticket.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
      } catch (Exception e) {
        System.out.println("An error occurred during Hibernate init: " + e);
      }
    }
    return sessionFactory;
  }

  @Override
  public String getId() {
    return this.classId;
  }
}
