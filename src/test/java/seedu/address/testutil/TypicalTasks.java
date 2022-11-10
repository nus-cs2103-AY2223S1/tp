package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.TEST_CATEGORY_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.TEST_CATEGORY_OTHERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_B;

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

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task TASK_AMY =
            new TaskBuilder().withName(VALID_TASK_NAME_A).withDescription(new Description(VALID_DESCRIPTION_A))
                    .withCategory(TEST_CATEGORY_FRONTEND).withPriority(PriorityEnum.LOW)
                    .withDeadline(LocalDate.parse(VALID_DEADLINE_A)).withStatus(false).build();
    public static final Task TASK_BOB =
            new TaskBuilder().withName(VALID_TASK_NAME_B).withDescription(new Description(VALID_DESCRIPTION_B))
                    .withCategory(TEST_CATEGORY_OTHERS).withPriority(PriorityEnum.MEDIUM)
                    .withDeadline(LocalDate.parse(VALID_DEADLINE_B)).withStatus(true).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.OTHERS);
    private static final TaskCategory testCat2 = new TaskCategory(TaskCategoryType.BACKEND);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());


    public static final Task TASK_ALICE = new Task(new TaskName("A"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_BENSON = new Task(new TaskName("B"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_CARL = new Task(new TaskName("C"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_DANIEL = new Task(new TaskName("D"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_ELLE = new Task(new TaskName("E"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_FIONA = new Task(new TaskName("F"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task TASK_GEORGE = new Task(new TaskName("G"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);


    // Manually added
    public static final Task HOON = new Task(new TaskName("H"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task IDA = new Task(new TaskName("I"),
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
        return new ArrayList<>(Arrays.asList(TASK_ALICE, TASK_BENSON, TASK_CARL, TASK_DANIEL, TASK_ELLE, TASK_FIONA,
                TASK_GEORGE, HILLARY, IVY));
    }

}
