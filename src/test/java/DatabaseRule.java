import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/registrar_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStudentsQuery = "DELETE FROM students *;";
      String deleteClassQuery = "DELETE FROM classes *;";
      String deleteClasses_StudentsQuery = "DELETE FROM classes_students *;";
      con.createQuery(deleteStudentsQuery).executeUpdate();
      con.createQuery(deleteClassQuery).executeUpdate();
      con.createQuery(deleteClasses_StudentsQuery).executeUpdate();
    }
  }
}
