package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.logic.task.Description;
import seedu.address.logic.task.Priority;
import seedu.address.logic.task.PriorityEnum;
import seedu.address.logic.task.Task;
import seedu.address.logic.task.TaskCategory;
import seedu.address.logic.task.TaskCategoryType;
import seedu.address.logic.task.TaskDeadline;
import seedu.address.logic.task.TaskList;
import seedu.address.logic.task.TaskName;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;



/**
 * Typical tasks, used for testing purposes
 */
public class TypicalTasks {

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private static final TaskCategory testCat = new TaskCategory(3, TaskCategoryType.OTHERS);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet());


    public static final Task ALICE = new Task(new TaskName("A"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task BENSON = new Task(new TaskName("B"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task CARL = new Task(new TaskName("C"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task DANIEL = new Task(new TaskName("D"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task ELLE = new Task(new TaskName("E"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task FIONA = new Task(new TaskName("F"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task GEORGE = new Task(new TaskName("G"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);

    // Manually added
    public static final Task HOON = new Task(new TaskName("H"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task IDA = new Task(new TaskName("I"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new Task(new TaskName("Amy"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    public static final Task BOB = new Task(new TaskName("Bob"),
            testCat, testDisc, testPriority, testDeadline, testPerson, false);
    private TaskList testList = new TaskList();

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (seedu.address.logic.task.Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
