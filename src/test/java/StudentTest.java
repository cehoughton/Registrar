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

   @Test
    public void equals_returnsTrueIfEnrollmentDatesAreTheSame() {
      Student firstStudent = new Student("Public", "Jim", "2016-02-24");
      Student secondStudent = new Student("Public", "Jim", "2016-02-24");
      assertEquals(firstStudent.getDate(), secondStudent.getDate());
    }

    @Test
    public void save_returnsTrueIfLastNamesAreTheSame() {
      Student myStudent = new Student("Public", "Jim", "2016-02-24");
      myStudent.save();
      assertTrue(Student.all().get(0).equals(myStudent));
    }

    @Test
    public void save_assingsIdToObject() {
      Student myStudent = new Student("Public", "Jim", "2016-02-24");
      myStudent.save();
      Student savedStudent = Student.all().get(0);
      assertEquals(myStudent.getId(), savedStudent.getId());
    }

    @Test
    public void find_findsStudentsInDatabase_true() {
      Student myStudent = new Student("Public", "Jim", "2016-02-24");
      myStudent.save();
      Student savedStudent = Student.find(myStudent.getId());
      assertTrue(myStudent.equals(savedStudent));
    }

    @Test
    public void addCourse_addsCourseToStudent() {
      Course myCourse = new Course("philosophy", 101);
      myCourse.save();

      Student myStudent = new Student("Public", "Jim", "2016-02-24");
      myStudent.save();

      myStudent.addCourse(myCourse);
      Course savedCourse = myStudent.getCourses().get(0);
      assertTrue(myCourse.equals(savedCourse));
    }

    @Test
    public void getCourses_returnsAllCourses_ArrayList() {
      Course myCourse = new Course("philosophy", 101);
      myCourse.save();

      Student myStudent = new Student("Public", "Jim", "2016-02-24");
      myStudent.save();

      myStudent.addCourse(myCourse);
      List savedCourses = myStudent.getCourses();
      assertEquals(savedCourses.size(), 1);
    }
}
