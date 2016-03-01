import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StudentTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
   public void equals_returnsTrueIfLastNameAreTheSame() {
     Student firstStudent = new Student("Public", "Jim", "2016-02-24");
     Student secondStudent = new Student("Public", "Jim", "2016-02-24");
     assertEquals(firstStudent.getLastname(), secondStudent.getLastname());
   }
}
