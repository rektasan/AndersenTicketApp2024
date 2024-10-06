package Model.User;

import Interfaces.Identifiable;

public abstract class User implements Identifiable {
  protected int classId;
  protected String role;

  public abstract void printRole();
}
