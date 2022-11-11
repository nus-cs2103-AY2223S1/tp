package seedu.address.testutil;

import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_CONTACT_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_CONTACT_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_DEADLINE_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_PROJECT_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_PROJECT_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TITLE_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TITLE_WORKSHOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskPanel;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_ONE =
            new TaskBuilder()
                    .withTitle("Add tasks to list")
                    .withCompleted(false)
                    .withDeadline("UNSPECIFIED")
                    .withContacts("Alice Pauline")
                    .build();
    public static final Task TASK_TWO =
            new TaskBuilder()
                    .withTitle("Assign contacts to task")
                    .withCompleted(false)
                    .withDeadline("1 January 2023")
                    .withProject("CS2103T")
                    .withContacts("George Best")
                    .build();
    public static final Task TASK_THREE = new TaskBuilder().withTitle("Set deadline for a task").build();
    public static final Task TASK_FOUR = new TaskBuilder().withTitle("Set tags for a task").build();
    public static final Task TASK_FIVE = new TaskBuilder().withTitle("Last task").build();

    public static final Task HOMEWORK = new TaskBuilder()
            .withTitle(VALID_TITLE_HOMEWORK)
            .withDeadline(VALID_DEADLINE_HOMEWORK)
            .withProject(VALID_PROJECT_HOMEWORK)
            .withContacts(VALID_CONTACT_HOMEWORK).build();

    public static final Task WORKSHOP = new TaskBuilder()
            .withTitle(VALID_TITLE_WORKSHOP)
            .withDeadline(VALID_DEADLINE_WORKSHOP)
            .withProject(VALID_PROJECT_WORKSHOP)
            .withContacts(VALID_CONTACT_WORKSHOP).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical tasks.
     */
    public static TaskPanel getTypicalTaskPanel() {
        TaskPanel tp = new TaskPanel();
        for (Task task : getTypicalTasks()) {
            tp.addTask(task);
        }
        return tp;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_ONE, TASK_TWO, TASK_THREE));
    }
}
