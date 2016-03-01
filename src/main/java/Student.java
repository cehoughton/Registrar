import java.util.List;
import org.sql2o.*;


public class Student {
  private int id;
  private String last_name;
  private String first_name;
  private String date;

  public int getId() {
    return id;
  }

  public String getLastname() {
    return last_name;
  }

  public String getFirstname() {
    return first_name;
  }

  public String getDate() {
    return date;
  }

  public Student(String last_name, String first_name, String date) {
    this.last_name = last_name;
    this.first_name = first_name;
    this.date = date;
  }

  public static List<Student> all() {
    String sql = "SELECT id, last_name, first_name, date FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }
}
