import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Course {
  private int id;
  private String dept;
  private int course_level;

  public int getId() {
    return id;
  }

  public String getDept() {
    return dept;
  }

  public int getCourseLevel() {
    return course_level;
  }

  public Course(String dept, int course_level) {
    this.dept = dept;
    this.course_level = course_level;
  }

  public static List<Course> all() {
    String sql = "SELECT id, name FROM Courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  @Override
  public boolean equals(Object otherCourse){
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getDept().equals(newCourse.getDept()) &&
             this.getCourseLevel() == newCourse.getCourseLevel();
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO courses(dept, course_level) VALUES (:dept, :course_level)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("dept", this.dept)
      .addParameter("course_level", this.course_level)
      .executeUpdate()
      .getKey();
  }
}




}
