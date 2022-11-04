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

public class SortByDeadlineTest {
    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.BACKEND);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final TaskDeadline testDeadline2 = new TaskDeadline(LocalDate.of(2023, 12,
            12));
    private static final TaskDeadline testDeadline3 = new TaskDeadline(LocalDate.of(2024, 12,
            12));
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());
    public static final Task ALICE = new Task(new TaskName("A"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task BENSON = new Task(new TaskName("B"),
            testDisc, testPriority, testCat, testDeadline2, testPerson, false);
    public static final Task CARL = new Task(new TaskName("C"),
            testDisc, testPriority, testCat, testDeadline3, testPerson, false);
    private SortByDeadline test = new SortByDeadline();

    @Test
    public void sortTasksTest() {
        assertEquals(-1, test.compare(BENSON, CARL));
    }

    @Test
    public void sortTasksTest2() {
        assertEquals(1, test.compare(BENSON, ALICE));
    }


}
