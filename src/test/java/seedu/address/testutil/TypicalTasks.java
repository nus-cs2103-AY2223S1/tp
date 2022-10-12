package seedu.address.testutil;

import seedu.address.logic.task.*;
import seedu.address.model.AddressBook;
import seedu.address.model.person.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TypicalTasks {

    TaskList testList = new TaskList();
    static TaskCategory testCat = new TaskCategory(3, TaskCategoryType.OTHERS);
    static Description testDisc = new Description("Test");
    static Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    static TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    static Person testPerson = new Person(new Name("test"), new Phone("99999999"), new Email("test@gmail.com"),new Address("test"), new HashSet());


    public static final Task ALICE = new Task(new TaskName("A"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task BENSON = new Task(new TaskName("B"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task CARL = new Task(new TaskName("C"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task DANIEL = new Task(new TaskName("D"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task ELLE = new Task(new TaskName("E"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task FIONA = new Task(new TaskName("F"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task GEORGE = new Task(new TaskName("G"),testCat,testDisc,testPriority, testDeadline,testPerson,false);

    // Manually added
    public static final Task HOON = new Task(new TaskName("H"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task IDA = new Task(new TaskName("I"),testCat,testDisc,testPriority, testDeadline,testPerson,false);

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new Task(new TaskName("Amy"),testCat,testDisc,testPriority, testDeadline,testPerson,false);
    public static final Task BOB = new Task(new TaskName("Bob"),testCat,testDisc,testPriority, testDeadline,testPerson,false);

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (seedu.address.logic.task.Task Task : getTypicalTasks()) {
            ab.addTask(Task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
