package modtrekt.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import modtrekt.model.task.Task;
import modtrekt.testutil.DeadlineBuilder;
import modtrekt.testutil.TaskBuilder;

public class TaskOrderTest {

    // Tasks
    private final Task none = new TaskBuilder().build();
    private final Task noneLexiAfter = new TaskBuilder().withDescription("z").build();
    private final Task low = new TaskBuilder().withPriority(Task.Priority.LOW).build();
    private final Task medium = new TaskBuilder().withPriority(Task.Priority.MEDIUM).build();
    private final Task high = new TaskBuilder().withPriority(Task.Priority.HIGH).build();
    private final Task noneArch = new TaskBuilder().withIsArchived(true).build();
    private final Task lowArch = new TaskBuilder().withPriority(Task.Priority.LOW)
            .withIsArchived(true).build();
    private final Task mediumArch = new TaskBuilder().withPriority(Task.Priority.MEDIUM)
            .withIsArchived(true).build();
    private final Task highArch = new TaskBuilder().withPriority(Task.Priority.HIGH)
            .withIsArchived(true).build();

    // Deadlines
    private final Task dNone = new DeadlineBuilder().build();
    private final Task dNoneLexiAfter = new DeadlineBuilder().withDescription("z").build();
    private final Task dLow = new DeadlineBuilder().withPriority(Task.Priority.LOW).build();
    private final Task dMed = new DeadlineBuilder().withPriority(Task.Priority.MEDIUM).build();
    private final Task dHigh = new DeadlineBuilder().withPriority(Task.Priority.HIGH).build();
    private final Task dNoneArch = new DeadlineBuilder().withIsArchived(true).build();
    private final Task dLowArch = new DeadlineBuilder().withPriority(Task.Priority.LOW)
            .withIsArchived(true).build();
    private final Task dMedArch = new DeadlineBuilder().withPriority(Task.Priority.MEDIUM)
            .withIsArchived(true).build();
    private final Task dHighArch = new DeadlineBuilder().withPriority(Task.Priority.HIGH)
            .withIsArchived(true).build();
    // Different dates
    private final Task dNoneLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01)).build();
    private final Task dLowLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withPriority(Task.Priority.LOW).build();
    private final Task dMedLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withPriority(Task.Priority.MEDIUM).build();
    private final Task dHighLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withPriority(Task.Priority.HIGH).build();
    private final Task dNoneArchLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withIsArchived(true).build();
    private final Task dLowArchLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withIsArchived(true).withPriority(Task.Priority.LOW).build();
    private final Task dMedArchLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withIsArchived(true).withPriority(Task.Priority.MEDIUM).build();
    private final Task dHighArchLater = new DeadlineBuilder().withDueDate(LocalDate.of(2023, 01, 01))
            .withIsArchived(true).withPriority(Task.Priority.HIGH).build();

    List<Task> listBuilder(Task ...tasks) {
        List<Task> taskList = new ArrayList<Task>();
        for (Task task : tasks) {
            taskList.add(task);
        }
        taskList.sort(null);
        return taskList;
    }

    boolean isOrderedCorrectly(List<Task> tasks, Task ...correctOrder) {
        for (int i = 0; i < correctOrder.length; i++) {
            if (!(tasks.get(i).equals(correctOrder[i]))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void order_onlyTasks_byPriority() {
        List<Task> taskList = listBuilder(none, medium, low, high);
        assertTrue(isOrderedCorrectly(taskList, high, medium, low, none));
    }

    @Test
    public void order_tasksDeadlines_priorityFirstThenDeadlines() {
        List<Task> taskList = listBuilder(medium, high, dLow, low, dMed);
        assertTrue(isOrderedCorrectly(taskList, high, dMed, medium, dLow, low));
    }

    @Test
    public void order_tasksDeadlines_archivedAlwaysOnBottom() {
        List<Task> taskList = listBuilder(dHighArch, highArch, mediumArch, none, dNone);
        assertTrue(isOrderedCorrectly(taskList, dNone, none, dHighArch, highArch, mediumArch));
    }

    @Test
    public void order_tasksDeadlines_deadlinesByEarlierDate() {
        List<Task> taskList = listBuilder(dMedLater, dMed, medium, dHighArchLater, dHighArch);
        assertTrue(isOrderedCorrectly(taskList,
                dMed, dMedLater, medium, dHighArch, dHighArchLater));
    }

    @Test
    public void order_tasksDeadlines_lexigraphicalTiebreaks() {
        List<Task> taskList = listBuilder(none, noneLexiAfter, dNoneLexiAfter, dNone);
        assertTrue(isOrderedCorrectly(taskList, dNone, dNoneLexiAfter, none, noneLexiAfter));
    }

    @Test
    public void order_all_archiveThenPriorityThenDeadlinesThenDuedateThenLexi() {
        List<Task> taskList = listBuilder(none, noneLexiAfter, low, medium, high, noneArch,
                lowArch, mediumArch, highArch, dNone, dNoneLexiAfter, dLow, dMed, dHigh,
                dNoneArch, dLowArch, dMedArch, dHighArch, dNoneLater, dLowLater, dMedLater,
                dHighLater, dNoneArchLater, dLowArchLater, dMedArchLater, dHighArchLater);
        assertTrue(isOrderedCorrectly(taskList,
                // Unarchived deadlines high
                dHigh, dHighLater,
                // Unarchived tasks high
                high,
                // Unarchived deadlines medium
                dMed, dMedLater,
                // Unarchived tasks medium
                medium,
                // Unarchived deadlines low
                dLow, dLowLater,
                // Unarchived tasks low
                low,
                // Unarchived deadlines none
                dNone, dNoneLexiAfter, dNoneLater,
                // Unarchived tasks none
                none, noneLexiAfter,
                // Archived deadlines high
                dHighArch, dHighArchLater,
                // Archived tasks high
                highArch,
                // Archived deadlines medium
                dMedArch, dMedArchLater,
                // Archived tasks medium
                mediumArch,
                // Archived deadlines low
                dLowArch, dLowArchLater,
                // Unarchived tasks low
                lowArch,
                // Unarchived deadlines none
                dNoneArch, dNoneArchLater,
                // Unarchived tasks none
                noneArch
                ));
    }
}
