package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;
import static seedu.address.testutil.TypicalTasks.FINISH_TP_ISH;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

public class TaskListTest {

    private final TaskList addressBook = new TaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskList_replacesData() {
        TaskList newData = getTypicalTaskList();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        List<Task> newTasks = Arrays.asList(FINISH_TP, FINISH_TP_ISH);
        TaskListStub newData = new TaskListStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(addressBook.hasTask(FINISH_TP));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        addressBook.addTask(FINISH_TP);
        assertTrue(addressBook.hasTask(FINISH_TP));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskList_returnsTrue() {
        addressBook.addTask(FINISH_TP);
        assertTrue(addressBook.hasTask(FINISH_TP_ISH));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskList whose tasks list can violate interface constraints.
     */
    private static class TaskListStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
