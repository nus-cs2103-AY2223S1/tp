package taskbook.testutil;


import static taskbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taskbook.model.TaskBook;
import taskbook.model.person.Person;
import taskbook.model.task.Deadline;
import taskbook.model.task.Event;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class containing a list of {@code Task} and {@code Person} objects to be used in tests.
 */
public class TypicalTaskBook {

    // Typical Persons
    /**
    * Do not add any tasks to Zed. Leave Zed as the *first* element in {@link TypicalTaskBook#getTypicalTasks()} .
    * Changing these will break the testcases which rely on Zed *not* having tests and being the first element.
    */
    public static final Person ZED = new PersonBuilder().withName("Zed HatesTasks")
        .withAddress("NUS School of Computing, COM1, 13, Computing Dr, 117417").withEmail("zed@example.com")
        .withPhone("91234567").build();
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    // Typical Dates
    public static final LocalDate NOV_NINE = LocalDate.of(2022, 11, 9);
    public static final LocalDate OCT_ELEVEN = LocalDate.of(2022, 10, 11);

    // Typical Tasks
    public static final Deadline EATING = new DeadlineBuilder().withPersonName(ALICE)
            .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(true)
            .withDeadlineDate(NOV_NINE).build();

    public static final Todo SLEEPING = new TodoBuilder().withPersonName(BENSON)
            .withAssignment(Assignment.FROM).withDescription("sleep early").withIsDone(false)
            .build();

    public static final Event PARTYING = new EventBuilder().withPersonName(CARL)
            .withAssignment(Assignment.TO).withDescription("party at kevin's house")
            .withEventDate(OCT_ELEVEN).withIsDone(true).build();

    private TypicalTaskBook() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical persons.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        try {
            for (Person person : getTypicalPersons()) {
                tb.addPerson(person);
            }
            for (Task task : getTypicalTasks()) {
                tb.addTask(task);
            }
            return tb;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return tb;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ZED, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(EATING, SLEEPING, PARTYING));
    }
}
