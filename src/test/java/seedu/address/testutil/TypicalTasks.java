package seedu.address.testutil;


import static seedu.address.testutil.TypicalTeams.TYPICAL_MEMBERS;
import static seedu.address.testutil.TypicalTeams.TYPICAL_MEMBERS_CARL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_1 = new Task(new TaskName("task"), List.of(), false, null);
    public static final Task TASK_1_DUPLICATED =
            new Task(new TaskName("task"), List.of(), false, null);
    public static final Task TASK_2 = new Task(new TaskName("task 123"), List.of(), false, null);
    public static final Task TASK_2_EDITED = new Task(new TaskName("task 123"), List.of(TypicalPersons.ALICE),
            false, LocalDateTime.of(2022, 12, 12, 23, 59));
    public static final Task TASK_3 = new Task(new TaskName("task three"), List.of(TypicalPersons.ALICE), false,
            LocalDateTime.of(2022, 12, 12, 23, 59));

    public static final Task TASK_ONLY_ALICE = new Task(new TaskName("task alice"), List.of(TypicalPersons.ALICE),
            false, LocalDateTime.of(2022, 12, 12, 23, 59));
    public static final Task TASK_3_NO_DEADLINE = new Task(new TaskName("task three"), List.of(TypicalPersons.ALICE),
            false,
            null);
    public static final Task TASK_CODE = new Task(new TaskName("code"), List.of(), false, null);

    public static final Task TASK_REVIEW = new Task(new TaskName("review"), List.of(), false, null);
    public static final Task TASK_1_DETAILS = new Task(new TaskName("task"),
            TYPICAL_MEMBERS, false,
            LocalDateTime.parse("2023-12-25 23:59", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));

    public static final Task TASK_2_DETAILS = new Task(new TaskName("task 123"),
            TYPICAL_MEMBERS_CARL, false,
            LocalDateTime.parse("2023-12-25 23:59", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));

    public static final Task TASK_2_NO_ASSIGNEE = new Task(new TaskName("two"),
            List.of(), false,
            LocalDateTime.parse("2023-12-25 23:59", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
    public static final Task TASK_MARKED = new Task(new TaskName("task"),
            List.of(), true, null);
}
