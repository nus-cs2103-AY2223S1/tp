//package seedu.address.model.grade;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import seedu.address.model.student.Student;
//import seedu.address.model.task.Task;
//
//class GradeTest {
//
//    private @Mock Student student;
//    private @Mock Task task;
//    private Grade underTest;
//
//    @BeforeEach
//    void setUp() {
//        student = mock(Student.class);
//        task = mock(Task.class);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void initiallyGraded_setAsGraded() {
//        underTest = new Grade(student, task, true);
//        underTest.setAsGraded();
//        assertTrue(underTest.isGraded());
//    }
//
//    @Test
//    void initiallyNotGraded_setAsGraded() {
//        underTest = new Grade(student, task);
//        underTest.setAsGraded();
//        assertTrue(underTest.isGraded());
//    }
//
//    @Test
//    void initiallyNotGraded_setAsNotGraded() {
//        underTest = new Grade(student, task);
//        underTest.setAsNotGraded();
//        assertFalse(underTest.isGraded());
//    }
//
//    @Test
//    void initiallyGraded_setAsNotGraded() {
//        underTest = new Grade(student, task, true);
//        underTest.setAsNotGraded();
//        assertFalse(underTest.isGraded());
//    }
//
//    @Test
//    void twoParameterConstructor_isGraded() {
//        underTest = new Grade(student, task);
//        assertFalse(underTest.isGraded());
//    }
//
//    @Test
//    void threeParameterConstructorFalse_isGraded() {
//        underTest = new Grade(student, task, false);
//        assertFalse(underTest.isGraded());
//    }
//
//    @Test
//    void threeParameterConstructorTrue_isGraded() {
//        underTest = new Grade(student, task, true);
//        assertTrue(underTest.isGraded());
//    }
//}
