import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;


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

  @Override
  public boolean equals(Object otherStudent) {
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getLastname().equals(newStudent.getLastname()) &&
             this.getFirstname().equals(newStudent.getFirstname()) &&
             this.getDate().equals(newStudent.getDate()) &&
             this.getId() == newStudent.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students (last_name, first_name, date) VALUES (:last_name, :first_name, :date)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("last_name", this.last_name)
      .addParameter("first_name", this.first_name)
      .addParameter("date", this.date)
      .executeUpdate()
      .getKey();
    }
  }

  public static Student find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Students where id=:id";
      Student student = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Student.class);
    return student;
    }
 }

  public void addCourse(Course course) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO courses_students (course_id, student_id) VALUES (:course_id, :student_id)";
    con.createQuery(sql)
      .addParameter("course_id", course.getId())
      .addParameter("student_id", this.getId())
      .executeUpdate();
    }
  }

  public ArrayList<Course> getCourses() {
  try(Connection con = DB.sql2o.open()){
  String sql = "SELECT course_id FROM courses_students WHERE student_id = :student_id";
  List<Integer> courseIds = con.createQuery(sql)
    .addParameter("student_id", this.getId())
    .executeAndFetch(Integer.class);

  ArrayList<Course> courses = new ArrayList<Course>();

    for (Integer courseId : courseIds) {
        String courseQuery = "Select * From courses WHERE id = :courseId";
        Course course = con.createQuery(courseQuery)
          .addParameter("courseId", courseId)
          .executeAndFetchFirst(Course.class);
        courses.add(course);
      }
    return courses;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM students WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM courses_students WHERE student_id = :studentId";
        con.createQuery(joinDeleteQuery)
          .addParameter("studentId", this.getId())
          .executeUpdate();
    }
  }
}
