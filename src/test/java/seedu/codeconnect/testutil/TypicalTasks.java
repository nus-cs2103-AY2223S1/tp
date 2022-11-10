package seedu.codeconnect.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.codeconnect.model.TaskList;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.task.Deadline;
import seedu.codeconnect.model.task.Status;
import seedu.codeconnect.model.task.Task;
import seedu.codeconnect.model.task.TaskName;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task FINISH_TUTORIAL = new Task(
            new TaskName("Finish tutorial 6"),
            new Module("MA1521"),
            new Deadline("2022-11-14 00:00"),
            new Status(false)
    );

    public static final Task WATCH_LECTURE = new Task(
            new TaskName("Watch week 5 lecture"),
            new Module("CS2103T"),
            new Deadline("2022-11-19 00:00"),
            new Status(false)
    );

    public static final Task FINISH_TP = new Task(
            new TaskName("Finish TP"),
            new Module("CS2103T"),
            new Deadline("2022-11-15 00:00"),
            new Status(false)
    );

    public static final Task BUY_GROCERIES = new Task(
            new TaskName("Buy Groceries"),
            new Module("-"),
            new Deadline("2022-07-15 00:00"),
            new Status(false)
    );

    /** Weakly but not strongly equivalent to FINISH_TP. */
    public static final Task FINISH_TP_ISH = new Task(
            new TaskName("Finish TP"),
            new Module("CS2103T"),
            new Deadline("2022-12-12 12:00"),
            new Status(true)
    );

    public static final Task LAB_2 = new Task(
            new TaskName("Lab 2"),
            new Module("CS2030S"),
            new Deadline("2022-02-02 23:59"),
            new Status(false)
    );

    // This one is not in typicalTasks so that it can be added without issues

    public static final Task LAB_3 = new Task(
            new TaskName("Lab 3"),
            new Module("CS2030S"),
            new Deadline("2022-03-15 23:59"),
            new Status(true)
    );

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FINISH_TUTORIAL, WATCH_LECTURE));
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }
}
