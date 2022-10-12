package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.task.*;
import seedu.address.model.person.*;
import seedu.address.logic.task.exceptions.DuplicateTaskException;
import seedu.address.logic.task.exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


public class TaskListTest {

    TaskList testList = new TaskList();
    TaskName testName = new TaskName("Test");
    TaskCategory testCat = new TaskCategory(3,TaskCategoryType.OTHERS);
    Description testDisc = new Description("Test");
    Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    Person testPerson = new Person(new Name("test"), new Phone("99999999"), new Email("test@gmail.com"),new Address("test"), new HashSet());
    Task testTask = new Task(testName,testCat,testDisc,testPriority, testDeadline,testPerson,true);

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().contains(null));
    }

    @Test
    public void add_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().contains(null));
    }

    @Test
    public void add_DuplicateTaskException() {

        testList.add(testTask);
        assertThrows(DuplicateTaskException.class, () -> testList.add(testTask));
    }

    @Test
    public void delete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().delete(null));
    }

    @Test
    public void delete_throwsDuplicateTaskException() {
        assertThrows(TaskNotFoundException.class, () -> new TaskList().delete(testTask));
    }

    @Test
    public void edit_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().edit(null, testTask));
        assertThrows(NullPointerException.class, () -> new TaskList().edit(testTask, null));
    }

    @Test
    public void edit_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> new TaskList().edit(testTask, testTask));
    }

    @Test
    public void edit_throwsDuplicateTaskException() {
        TaskList test = new TaskList();
        test.add(testTask);
        assertThrows(DuplicateTaskException.class, () -> test.edit(testTask, testTask));
    }

    @Test
    public void find_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().find(null));
    }

    @Test
    public void find_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> new TaskList().find(testTask));
    }

    @Test
    public void filter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().filter((Priority) null));
        assertThrows(NullPointerException.class, () -> new TaskList().filter((TaskCategory) null));
        assertThrows(NullPointerException.class, () -> new TaskList().filter((TaskDeadline) null));
    }

    @Test
    public void setTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().setTasks((TaskList) null));
        assertThrows(NullPointerException.class, () -> new TaskList().setTasks((List<Task>) null));
    }

}
