package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskList;
import seedu.address.model.task.TaskName;

/**
 * Typical tasks, used for testing purposes
 */
public class TypicalTasks {

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.OTHERS);
    private static final TaskCategory testCat2 = new TaskCategory(TaskCategoryType.BACKEND);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());


    public static final Task ALICE = new Task(new TaskName("A"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task BENSON = new Task(new TaskName("B"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task CARL = new Task(new TaskName("C"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task DANIEL = new Task(new TaskName("D"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task ELLE = new Task(new TaskName("E"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task FIONA = new Task(new TaskName("F"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task GEORGE = new Task(new TaskName("G"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);


    // Manually added
    public static final Task HOON = new Task(new TaskName("H"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task IDA = new Task(new TaskName("I"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new Task(new TaskName("Amy"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task BOB = new Task(new TaskName("Bob"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);

    //added for filterTask testing
    public static final Task HILLARY = new Task(new TaskName("H"),
            testDisc, testPriority, testCat2, testDeadline, testPerson, false);
    public static final Task IVY = new Task(new TaskName("I"),
            testDisc, testPriority, testCat2, testDeadline, testPerson, false);
    private final TaskList testList = new TaskList();

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        ab.addPerson(testPerson);
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HILLARY, IVY));
    }

}
