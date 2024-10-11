package com.jfb.tickets.model.user;

import com.jfb.tickets.util.interfaces.Identifiable;

public abstract class User implements Identifiable {

  protected String role;

  public abstract void printRole();

}
