package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.teammate.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskPanelTest {

    private final TaskPanel taskPanel = new TaskPanel();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskPanel.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskPanel.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskPanel_replacesData() {
        TaskPanel newData = getTypicalTaskPanel();
        taskPanel.resetData(newData);
        assertEquals(newData, taskPanel);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        // TODO: Update
        Task editedTaskOne = new TaskBuilder(TASK_ONE).build();
        List<Task> newTasks = Arrays.asList(TASK_ONE, editedTaskOne);
        TaskPanelStub newData = new TaskPanelStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskPanel.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskPanel.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskPanel_returnsFalse() {
        assertFalse(taskPanel.hasTask(TASK_ONE));
    }

    @Test
    public void hasTask_taskInTaskPanel_returnsTrue() {
        taskPanel.addTask(TASK_ONE);
        assertTrue(taskPanel.hasTask(TASK_ONE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskPanel_returnsTrue() {
        // TODO: Update task
        taskPanel.addTask(TASK_ONE);
        Task editedTaskOne = new TaskBuilder(TASK_ONE).build();
        assertTrue(taskPanel.hasTask(editedTaskOne));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskPanel.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskPanel whose tasks list can violate interface constraints.
     */
    private static class TaskPanelStub implements ReadOnlyTaskPanel {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskPanelStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
