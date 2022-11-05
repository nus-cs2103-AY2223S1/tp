package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task READ_BOOK = new TaskBuilder()
            .withDescription("read book")
            .withDeadline("12-11-2022")
            .withCompletionStatus(false)
            .withId(1)
            .build();
    public static final Task WRITE_TEST_CASES = new TaskBuilder()
            .withDescription("write test cases")
            .withDeadline("21-09-2022")
            .withId(2)
            .withCompletionStatus(true)
            .withTags("CS2103T")
            .build();
    public static final Task TAKE_A_BREAK = new TaskBuilder()
            .withDescription("take a break")
            .withDeadline("25-12-2029")
            .withId(3)
            .withArchivalStatus(true)
            .withTags("CS2103T")
            .build();
    public static final Task PREPARE_SLIDES = new TaskBuilder()
            .withDescription("prepare slides")
            .withDeadline("28-10-2022")
            .withId(4)
            .withCompletionStatus(true)
            .withArchivalStatus(true)
            .withTags("CS2101")
            .build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(READ_BOOK, WRITE_TEST_CASES, TAKE_A_BREAK, PREPARE_SLIDES));
    }

    public static List<Task> getTypicalTasksSortedByDeadline() {
        return new ArrayList<>(Arrays.asList(WRITE_TEST_CASES, PREPARE_SLIDES, READ_BOOK, TAKE_A_BREAK));
    }
}
