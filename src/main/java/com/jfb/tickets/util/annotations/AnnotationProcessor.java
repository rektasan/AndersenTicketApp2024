package com.jfb.tickets.util.annotations;

import java.lang.reflect.Field;

public class AnnotationProcessor {

  public static void processAnnotations(Object obj) {

    Class<?> klass = obj.getClass();

    for (Field field : klass.getDeclaredFields()) {
      if (field.isAnnotationPresent(NullableWarning.class)) {
        String message = String.format("Variable %s is null in class %s", field.getName(), klass.getSimpleName());
        System.err.println(message);
      }
    }
  }

}
