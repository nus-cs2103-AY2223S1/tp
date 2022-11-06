package jarvis.testutil;

import static jarvis.logic.commands.CommandTestUtil.VALID_DEADLINE_MISSION2;
import static jarvis.logic.commands.CommandTestUtil.VALID_DEADLINE_QUEST2;
import static jarvis.logic.commands.CommandTestUtil.VALID_DESC_MISSION2;
import static jarvis.logic.commands.CommandTestUtil.VALID_DESC_QUEST2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jarvis.model.Task;
import jarvis.model.TaskBook;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task MISSION1 = new TaskBuilder().withDesc("Mark mission 1")
            .withDeadline(LocalDate.of(2022, 10, 23)).build();
    public static final Task STUDIO = new TaskBuilder().withDesc("Prepare studio 1 slides").build();
    public static final Task QUEST1 = new TaskBuilder().withDesc("Check quest 1").build();
    public static final Task MASTERY_CHECK = new TaskBuilder().withDesc("Conduct mastery check")
            .withDeadline(LocalDate.of(2022, 9, 12)).build();

    // Manually added
    public static final Task FOLLOW_UP = new TaskBuilder().withDesc("Follow up on question").build();
    public static final Task REPLY_STUDENTS = new TaskBuilder().withDesc("Reply student")
            .withDeadline(LocalDate.of(2022, 10, 31)).build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task MISSION2 = new TaskBuilder().withDesc(VALID_DESC_MISSION2)
            .withDeadline(VALID_DEADLINE_MISSION2).build();
    public static final Task QUEST2 = new TaskBuilder().withDesc(VALID_DESC_QUEST2)
            .withDeadline(VALID_DEADLINE_QUEST2).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        for (Task task : getTypicalTasks()) {
            tb.addTask(task);
        }
        return tb;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MISSION1, STUDIO, QUEST1, MASTERY_CHECK));
    }

}
