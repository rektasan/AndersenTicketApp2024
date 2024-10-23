package com.jfb.ticket_app.util.annotations;

import com.jfb.ticket_app.util.interfaces.Identifiable;
import java.lang.reflect.Field;

public class AnnotationProcessor implements Identifiable {

  private final String ID = generateId();

  @Override
  public String getId() {
    return this.ID;
  }

  public static void processAnnotations(Object obj) {

    Class<?> klass = obj.getClass();

    for (Field field : klass.getDeclaredFields()) {
      if (field.isAnnotationPresent(NullableWarning.class)) {
        String message = String.format("Variable %s is null in class %s", field.getName(), klass.getSimpleName());
        System.out.println(message);
      }
    }
  }
}
