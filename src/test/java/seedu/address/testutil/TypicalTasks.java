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

    public static final Task READ_BOOK = new TaskBuilder().withDescription("read book").withDeadline("12-09-2022")
            .build();
    public static final Task DO_QUIZ = new TaskBuilder().withDescription("do quiz").withDeadline("11-12-2022")
            .withTags("CS2103T").build();
    public static final Task FINISH_REPORT = new TaskBuilder().withDescription("finish report")
            .withDeadline("10-11-2022").withStatus(true).withTags("CS2101").build();

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
        return new ArrayList<>(Arrays.asList(READ_BOOK, DO_QUIZ, FINISH_REPORT));
    }
}
