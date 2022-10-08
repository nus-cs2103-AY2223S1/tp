package modtrekt.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modtrekt.logic.commands.CommandTestUtil;
import modtrekt.model.TaskBook;
import modtrekt.model.task.Task;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Task TASK_1 = new TaskBuilder().withDescription("Alice Pauline").build();
    public static final Task TASK_2 = new TaskBuilder().withDescription("Benson Meier").build();
    public static final Task TASK_3 = new TaskBuilder().withDescription("Carl Kurz").build();
    public static final Task TASK_4 = new TaskBuilder().withDescription("Daniel Meier").build();
    public static final Task TASK_5 = new TaskBuilder().withDescription("Elle Meyer").build();
    public static final Task TASK_6 = new TaskBuilder().withDescription("Fiona Kunz").build();
    public static final Task TASK_7 = new TaskBuilder().withDescription("George Best").build();

    // Manually added
    public static final Task TASK_8 = new TaskBuilder().withDescription("Hoon Meier").build();
    public static final Task TASK_9 = new TaskBuilder().withDescription("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Task AMY =
            new TaskBuilder().withDescription(CommandTestUtil.VALID_NAME_AMY).build();
    public static final Task BOB =
            new TaskBuilder().withDescription(CommandTestUtil.VALID_NAME_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static TaskBook getTypicalAddressBook() {
        TaskBook ab = new TaskBook();
        for (Task t : getTypicalPersons()) {
            ab.addTask(t);
        }
        return ab;
    }

    public static List<Task> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4, TASK_5, TASK_6, TASK_7));
    }
}
