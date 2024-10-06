package Model.User;

public class Client extends User{

  public Client(int classId) {
    this.classId = classId;
    role = "client";
  }

  public void getTicket() {
    System.out.println(role + " is getting a ticket");
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

  @Override
  public void setClassId(int id) {
    this.classId = id;
  }

  @Override
  public void printRole() {
    System.out.println("Role :" + role);
  }
}
