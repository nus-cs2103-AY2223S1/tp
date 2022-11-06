package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ArchivedTaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 * TODO: Update names of the tasks
 */
public class TypicalPersons {

    public static final Task ALICE = new PersonBuilder().withName("Assignment 2")
            .withDeadline("2022-01-02")
            .withModule("CS2100")
            .withTags("highPriority")
            .withIsDone(false)
            .build();
    public static final Task BENSON = new PersonBuilder().withName("Lab 4")
            .withDeadline("2022-10-07")
            .withModule("CS2100")
            .withTags("low", "optional")
            .withIsDone(true).build();
    public static final Task CARL = new PersonBuilder().withName("Chapter 1 Revision")
            .withModule("CS2100")
            .withDeadline("2022-10-11")
            .withTags("revision")
            .withIsDone(true)
            .build();
    public static final Task DANIEL = new PersonBuilder().withName("Problem Set A")
            .withModule("CS2040S")
            .withDeadline("2022-10-09")
            .withIsDone(true)
            .build();
    public static final Task ELLE = new PersonBuilder().withName("Tutorial 2")
            .withModule("MA2001")
            .withDeadline("2022-09-09")
            .withIsDone(false).build();
    public static final Task FIONA = new PersonBuilder().withName("Tutorial 3")
            .withModule("MA2001")
            .withDeadline("2022-09-16")
            .withIsDone(false).build();
    public static final Task GEORGE = new PersonBuilder().withName("Tutorial 4")
            .withModule("MA2001")
            .withDeadline("2022-09-23")
            .withIsDone(true).build();

    //for Archived Task Book
    public static final Task CARLS = new PersonBuilder().withName("Chapter 2 Revision")
            .withModule("CS2100")
            .withDeadline("2022-11-11")
            .withTags("revision")
            .withIsDone(true)
            .build();
    public static final Task DAN = new PersonBuilder().withName("Problem Set C")
            .withModule("CS2040S")
            .withDeadline("2022-11-09")
            .withIsDone(true)
            .build();
    public static final Task EVE = new PersonBuilder().withName("Tutorial 7")
            .withModule("MA2001")
            .withDeadline("2022-12-09")
            .withIsDone(false).build();
    public static final Task FIONN = new PersonBuilder().withName("Tutorial 4")
            .withModule("MA2001")
            .withDeadline("2022-09-16")
            .withIsDone(false).build();
    public static final Task GEORGIA = new PersonBuilder().withName("Assignment 4")
            .withModule("MA2001")
            .withDeadline("2022-12-30")
            .withIsDone(true).build();

    // Manually added
    public static final Task HOON = new PersonBuilder().withName("Hoon Meier").withModule("CS2015")
            .withDeadline("2022-12-01").build();
    public static final Task IDA = new PersonBuilder().withName("Ida Mueller").withModule("CS2016")
            .withDeadline("2022-12-14").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task ALPHA = new PersonBuilder().withName(VALID_NAME_TASK_ALPHA)
            .withModule(VALID_MODULE_ALPHA).withDeadline(VALID_DEADLINE_ALPHA).withTags(VALID_TAG_TUTORIAL).build();
    public static final Task BETA = new PersonBuilder().withName(VALID_NAME_TASK_BETA).withModule(VALID_MODULE_BETA)
            .withDeadline(VALID_DEADLINE_BETA).withTags(VALID_TAG_HIGH_PRIORITY, VALID_TAG_TUTORIAL)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalPersons()) {
            ab.addPerson(task);
        }
        return ab;
    }

    public static ArchivedTaskBook getTypicalArchivedTaskBook() {
        ArchivedTaskBook atb = new ArchivedTaskBook();
        for (Task task: getTypicalArchivedTasks()) {
            atb.addTask(task);
        }
        return atb;
    }

    public static List<Task> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Task> getTypicalArchivedTasks() {
        return new ArrayList<>(Arrays.asList(CARLS, DAN, EVE, FIONN, GEORGIA));
    }

}
