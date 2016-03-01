import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/registrar_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStudentsQuery = "DELETE FROM students *;";
      String deleteCoursesQuery = "DELETE FROM courses *;";
      String deleteCourses_StudentsQuery = "DELETE FROM courses_students *;";
      con.createQuery(deleteStudentsQuery).executeUpdate();
      con.createQuery(deleteCoursesQuery).executeUpdate();
      con.createQuery(deleteCourses_StudentsQuery).executeUpdate();
    }
  }
}
