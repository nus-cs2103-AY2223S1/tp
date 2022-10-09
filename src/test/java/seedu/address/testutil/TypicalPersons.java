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
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 * TODO: Update names of the tasks
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Assignment 2")
            .withDeadline("2022-11-02")
            .withModule("CS2100")
            .withTags("highPriority")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Lab 4")
            .withDeadline("2022-10-07")
            .withModule("CS2100")
            .withTags("low", "optional")
            .withIsDone(true).build();
    public static final Person CARL = new PersonBuilder().withName("Chapter 1 Revision")
            .withModule("CS2103T")
            .withDeadline("2022-10-11")
            .withTags("revision")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Problem Set A")
            .withModule("CS2040S")
            .withDeadline("2022-10-09")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Tutorial 2")
            .withModule("MA2001")
            .withDeadline("2022-09-09")
            .withIsDone(true).build();
    public static final Person FIONA = new PersonBuilder().withName("Tutorial 3")
            .withModule("MA2001")
            .withDeadline("2022-09-16").build();
    public static final Person GEORGE = new PersonBuilder().withName("Tutorial 4")
            .withModule("MA2001")
            .withDeadline("2022-09-23").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withModule("CS2015")
            .withDeadline("2022-12-01").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withModule("CS2016")
            .withDeadline("2022-12-14").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person ALPHA = new PersonBuilder().withName(VALID_NAME_TASK_ALPHA)
            .withModule(VALID_MODULE_ALPHA).withDeadline(VALID_DEADLINE_ALPHA).withTags(VALID_TAG_TUTORIAL).build();
    public static final Person BETA = new PersonBuilder().withName(VALID_NAME_TASK_BETA).withModule(VALID_MODULE_BETA)
            .withDeadline(VALID_DEADLINE_BETA).withTags(VALID_TAG_HIGH_PRIORITY, VALID_TAG_TUTORIAL)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
