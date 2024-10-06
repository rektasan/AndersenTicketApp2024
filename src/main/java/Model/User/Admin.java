package Model.User;

public class Admin extends User {

  public Admin(int classId) {
    this.classId = classId;
    role = "admin";
  }

  public void checkTicket() {
    System.out.println("Ticket is being checked by " + role);
  }

  @Override
  public void printRole() {
    System.out.println("Role: " + role);
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }
}
