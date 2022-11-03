package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


public class TaskTest {

    //Same attributes as alice
    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.OTHERS);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());

    //different attributes as alice
    private static final TaskCategory testCat2 = new TaskCategory(TaskCategoryType.BACKEND);
    private static final Description testDisc2 = new Description("Test1");
    private static final Priority testPriority2 = new Priority(PriorityEnum.LOW);
    private static final TaskDeadline testDeadline2 = new TaskDeadline(LocalDate.of(2022,12,12));
    private static final Person testPerson2 = new Person(new Name("test"), new Phone("99999998"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null,
                null, null, null, null, null, false));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task task1 = new Task(new TaskName("A"),
                testDisc2, testPriority2, testCat2, testDeadline2, testPerson2, false);
        assertTrue(ALICE.isSameTask(task1));

        // different name, all other attributes same -> returns false
        Task task2 = new Task(new TaskName("B"),
                testDisc, testPriority, testCat, testDeadline, testPerson, false);
        assertFalse(ALICE.isSameTask(task2));

        // name differs in case, all other attributes same -> returns false
        Task task3 = new Task(new TaskName("a"),
                testDisc, testPriority, testCat, testDeadline, testPerson, false);
        assertFalse(ALICE.isSameTask(task3));

        // name has trailing spaces, all other attributes same -> returns false
        Task task4 = new Task(new TaskName("A   "),
                testDisc, testPriority, testCat, testDeadline, testPerson, false);
        assertFalse(ALICE.isSameTask(task4));
    }
}
