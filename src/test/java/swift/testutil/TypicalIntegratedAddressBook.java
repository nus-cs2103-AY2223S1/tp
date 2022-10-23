package swift.testutil;

import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import swift.model.AddressBook;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * A utility class containing a list of {@code PersonTaskBridge} objects to be
 * used in tests.
 */
public class TypicalIntegratedAddressBook {
    public static final String PERSON1_UUID = "c4c645da-27b3-454d-9428-5295a6ee1f33";
    public static final String PERSON2_UUID = "5f3f93b9-d839-4d5c-b197-9f3e53ebbb71";
    public static final String TASK1_UUID = "bfbf250c-fd58-49b4-be15-ca12095ca2ee";
    public static final String TASK2_UUID = "41478d11-dbba-441f-ab0f-99ddf097eac1";
    public static final String TASK3_UUID = "ebda2eaf-9100-4de2-85c0-cb3efed5685c";

    public static final PersonTaskBridge DEFAULT_BRIDGE_1 = new PersonTaskBridge(
            UUID.fromString(PERSON1_UUID),
            UUID.fromString(TASK1_UUID));
    public static final PersonTaskBridge DEFAULT_BRIDGE_2 = new PersonTaskBridge(
            UUID.fromString(PERSON1_UUID),
            UUID.fromString(TASK2_UUID));
    public static final PersonTaskBridge DEFAULT_BRIDGE_3 = new PersonTaskBridge(
            UUID.fromString(PERSON2_UUID),
            UUID.fromString(TASK3_UUID));

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withId(PERSON1_UUID).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withId(PERSON2_UUID).build();

    public static final Task BUY_MILK = new TaskBuilder()
            .withTaskName(VALID_TASK_NAME_1)
            .withId(TASK1_UUID).build();
    public static final Task CS2103T = new TaskBuilder()
            .withTaskName(VALID_TASK_NAME_2)
            .withId(TASK2_UUID).build();
    public static final Task FINISH_ASSIGNMENT = new TaskBuilder()
            .withTaskName(VALID_TASK_NAME_3)
            .withId(TASK3_UUID).build();

    private TypicalIntegratedAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (PersonTaskBridge bridge : getTypicalBridges()) {
            ab.addBridge(bridge);
        }
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        for (Person person : getTypicalPeople()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<PersonTaskBridge> getTypicalBridges() {
        return new ArrayList<>(Arrays.asList(DEFAULT_BRIDGE_1, DEFAULT_BRIDGE_2, DEFAULT_BRIDGE_3));
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BUY_MILK, CS2103T, FINISH_ASSIGNMENT));
    }

    public static List<Person> getTypicalPeople() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON));
    }
}
