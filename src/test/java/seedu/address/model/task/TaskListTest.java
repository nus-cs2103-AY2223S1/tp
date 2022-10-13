package seedu.address.model.task;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.task.Description;
import seedu.address.logic.task.Priority;
import seedu.address.logic.task.PriorityEnum;
import seedu.address.logic.task.Task;
import seedu.address.logic.task.TaskCategory;
import seedu.address.logic.task.TaskCategoryType;
import seedu.address.logic.task.TaskDeadline;
import seedu.address.logic.task.TaskList;
import seedu.address.logic.task.TaskName;
import seedu.address.logic.task.exceptions.DuplicateTaskException;
import seedu.address.logic.task.exceptions.TaskNotFoundException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;




public class TaskListTest {

    private TaskList testList = new TaskList();
    private TaskName testName = new TaskName("Test");
    private TaskCategory testCat = new TaskCategory(3, TaskCategoryType.OTHERS);
    private Description testDisc = new Description("Test");
    private Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet());
    private Task testTask = new Task(testName, testCat, testDisc, testPriority, testDeadline, testPerson, true);

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().contains(null));
    }

    @Test
    public void add_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList().contains(null));
    }

    @Test
    public void addDuplicateTaskException() {

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
