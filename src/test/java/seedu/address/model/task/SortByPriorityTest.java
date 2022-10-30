package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SortByPriorityTest {

    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.BACKEND);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final Priority testPriority2 = new Priority(PriorityEnum.HIGH);
    private static final Priority testPriority3 = new Priority(PriorityEnum.LOW);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());
    public static final Task ALICE = new Task(new TaskName("A"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task BENSON = new Task(new TaskName("B"),
            testDisc, testPriority2, testCat, testDeadline, testPerson, false);
    public static final Task CARL = new Task(new TaskName("C"),
            testDisc, testPriority3, testCat, testDeadline, testPerson, false);
    public static final Task DANIEL = new Task(new TaskName("D"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    private SortByPriority test = new SortByPriority();

    @Test
    public void sortTasksTest() {
        assertEquals(-1, test.compare(ALICE, BENSON));
    }

    @Test
    public void sortTasksTest2() {
        assertEquals(1, test.compare(ALICE, CARL));
    }

    @Test
    public void sortTasksTest3() {
        assertEquals(0, test.compare(ALICE, DANIEL));
    }


}
