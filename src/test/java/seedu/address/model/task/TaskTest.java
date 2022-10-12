package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import seedu.address.model.student.Student;


class TaskTest {


    private Task oneStudentTask;
    private Task everythingElseDifferent;
    private Task oneStudentTaskSameAttributes;
    private Task twoStudentsTask;
    private Task noStudentTask;
    private Task oneStudentTask1;
    private @Mock TaskName taskName;
    private @Mock TaskName taskName1;
    private @Mock TaskDescription taskDescription;
    private @Mock TaskDeadline taskDeadline;
    private Set<Student> oneStudentSet;
    private Set<Student> twoStudentsSet;
    private @Mock Student studentAlexa;
    private @Mock Student studentSiri;


    @BeforeEach
    public void setUp() {
        taskDeadline = mock(TaskDeadline.class);
        taskName = mock(TaskName.class);
        //when(taskName.equals(taskName)).thenReturn(true);
        taskDescription = mock(TaskDescription.class);

        when(taskName.toString()).thenReturn("Pass Test");
        when(taskDescription.toString()).thenReturn("Pass all the build tests.");
        when(taskDeadline.toString()).thenReturn("2020-30-02");

        studentAlexa = mock(Student.class);
        when(studentAlexa.toString()).thenReturn("Alexa");

        studentSiri = mock(Student.class);
        when(studentSiri.toString()).thenReturn("Siri");

        oneStudentSet = new HashSet<>();
        oneStudentSet.add(studentAlexa);

        oneStudentTask = new Task(taskName, taskDescription, taskDeadline, oneStudentSet);
        oneStudentTaskSameAttributes = new Task(taskName, taskDescription, taskDeadline, oneStudentSet);

        taskName1 = mock(TaskName.class);
        //when(taskName.equals(taskName1)).thenReturn(false);
        oneStudentTask1 = new Task(taskName1, taskDescription, taskDeadline, oneStudentSet);

        noStudentTask = new Task(taskName, taskDescription, taskDeadline, new HashSet<>());

        twoStudentsSet = new HashSet<>();
        twoStudentsSet.add(studentAlexa);
        twoStudentsSet.add(studentSiri);

        twoStudentsTask = new Task(taskName, taskDescription, taskDeadline, twoStudentsSet);
        everythingElseDifferent = new Task(taskName, mock(TaskDescription.class),
                mock(TaskDeadline.class), twoStudentsSet);
    }

    @Test
    void getTaskName() {
        assertEquals(oneStudentTask.getTaskName(), taskName);
    }

    @Test
    void getTaskDescription() {
        assertEquals(oneStudentTask.getTaskDescription(), taskDescription);
    }

    @Test
    void getTaskDeadline() {
        assertEquals(oneStudentTask.getTaskDeadline(), taskDeadline);
    }

    @Test
    void getStudents() {
        assertEquals(oneStudentTask.getStudents(), oneStudentSet);
    }

    @Test
    void sameReference_isSameTask() {
        assertTrue(noStudentTask.isSameTask(noStudentTask));
    }

    @Test
    void otherTaskIsNull_isSameTask() {
        assertFalse(oneStudentTask.isSameTask(null));
    }

    @Test
    void sameAttributes_isSameTask() {
        assertTrue(oneStudentTask.isSameTask(oneStudentTaskSameAttributes));
    }

    @Test
    void sameTaskName_otherAttributesDifferent_isSameTask() {
        assertTrue(oneStudentTask.isSameTask(everythingElseDifferent));
    }

    @Test
    void differentTaskName_otherAttributesSame_isSameTask() {
        assertFalse(oneStudentTask.isSameTask(oneStudentTask1));
    }

    @Test
    void sameReference_testEquals() {
        assertEquals(noStudentTask, noStudentTask);
    }

    @Test
    void sameTaskName_otherAttributesDifferent_testEquals() {
        assertNotEquals(everythingElseDifferent, oneStudentTask);
    }

    @Test
    void differentTaskName_otherAttributesSame_testEquals() {
        assertNotEquals(oneStudentTask1, oneStudentTask);
    }

    @Test
    void otherTaskIsNull_testEquals() {
        assertNotEquals(oneStudentTask, null);
    }

    @Test
    void sameAttributes_testEquals() {
        assertEquals(oneStudentTask, oneStudentTaskSameAttributes);
    }

    @Test
    void sameReference_testHashCode() {
        assertEquals(noStudentTask.hashCode(), noStudentTask.hashCode());
    }

    @Test
    void sameTaskName_otherAttributesDifferent_testHashCode() {
        assertNotEquals(everythingElseDifferent.hashCode(), oneStudentTask.hashCode());
    }

    @Test
    void differentTaskName_otherAttributesSame_testHashCode() {
        assertNotEquals(oneStudentTask1.hashCode(), oneStudentTask.hashCode());
    }


    @Test
    void sameAttributes_testHashCode() {
        assertEquals(oneStudentTask.hashCode(), oneStudentTaskSameAttributes.hashCode());
    }

    @Test
    void oneStudent_testToString() {
        String correct =
                "Pass Test Task Description: Pass all the build tests. Task Deadline: 2020-30-02 Students: Alexa";
        assertEquals(oneStudentTask.toString(), correct);
    }

    @Test
    void noStudent_testToString() {
        String correct =
                "Pass Test Task Description: Pass all the build tests. Task Deadline: 2020-30-02 Students: None";
        assertEquals(noStudentTask.toString(), correct);
    }

    @Test
    void twoStudents_testToString() {
        String correct =
                "Pass Test Task Description: Pass all the build tests. Task Deadline: 2020-30-02 Students: SiriAlexa";
        String alsoCorrect =
                "Pass Test Task Description: Pass all the build tests. Task Deadline: 2020-30-02 Students: AlexaSiri";
        assertTrue(isEither(twoStudentsTask.toString(), correct, alsoCorrect));
    }

    private boolean isEither(String toCheck, String correct, String alsoCorrect) {
        return toCheck.equals(correct) || toCheck.equals(alsoCorrect);
    }
}
