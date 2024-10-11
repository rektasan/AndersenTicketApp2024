package com.jfb.tickets.util.annotations;

import com.jfb.tickets.util.interfaces.Identifiable;
import java.lang.reflect.Field;

public class AnnotationProcessor implements Identifiable {

  private final int CLASS_ID = generateClassId();

  @Override
  public int getClassId() {
    return this.CLASS_ID;
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
