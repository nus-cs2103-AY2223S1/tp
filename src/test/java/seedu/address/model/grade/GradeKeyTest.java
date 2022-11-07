package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import seedu.address.model.student.Student;
import seedu.address.model.task.Task;



class GradeKeyTest {

    private @Mock Student student;
    private @Mock Task task;
    private GradeKey testKey;

    @BeforeEach
    public void setUp() {
        student = mock(Student.class);
        task = mock(Task.class);
        testKey = new GradeKey(student, task);
    }

    @Test
    void differentObject_testEquals() {
        assertNotEquals(testKey, new Object());
    }
    @Test
    void sameKey_testEquals() {
        GradeKey sameKey = new GradeKey(student, task);
        assertEquals(testKey, sameKey);
    }
    @Test
    void differentStudent_testEquals() {
        GradeKey differentStudentKey = new GradeKey(mock(Student.class), task);
        assertNotEquals(testKey, differentStudentKey);
    }

    @Test
    void differentTask_testEquals() {
        GradeKey differentTaskKey = new GradeKey(student, mock(Task.class));
        assertNotEquals(testKey, differentTaskKey);
    }

    @Test
    void differentBoth_testEquals() {
        GradeKey differentKey = new GradeKey(mock(Student.class), mock(Task.class));
        assertNotEquals(testKey, differentKey);
    }
    @Test
    void sameKey_testHashCode() {
        GradeKey sameKey = new GradeKey(student, task);
        assertEquals(testKey.hashCode(), sameKey.hashCode());
    }
    @Test
    void differentStudent_testHashCode() {
        GradeKey differentStudentKey = new GradeKey(mock(Student.class), task);
        assertNotEquals(testKey.hashCode(), differentStudentKey.hashCode());
    }

    @Test
    void differentTask_testHashCode() {
        GradeKey differentTaskKey = new GradeKey(student, mock(Task.class));
        assertNotEquals(testKey.hashCode(), differentTaskKey.hashCode());
    }

    @Test
    void differentBoth_testHashCode() {
        GradeKey differentKey = new GradeKey(mock(Student.class), mock(Task.class));
        assertNotEquals(testKey.hashCode(), differentKey.hashCode());
    }

    @Test
    void getStudent() {
        assertEquals(testKey.getStudent(), student);
    }

    @Test
    void getTask() {
        assertEquals(testKey.getTask(), task);
    }
}
