package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalArchivedTaskList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ArchivedTaskListTest {

    private final ArchivedTaskList archivedTaskList = new ArchivedTaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), archivedTaskList.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> archivedTaskList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyArchivedTaskBook_replacesData() {
        ArchivedTaskList newData = getTypicalArchivedTaskList();
        archivedTaskList.resetData(newData);
        assertEquals(newData, archivedTaskList);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicatePersonException() {
        // Two tasks with the same identity fields
        Task editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        ArchivedTaskListTest.ArchivedTaskBookStub newData = new ArchivedTaskListTest.ArchivedTaskBookStub(newTasks);

        assertThrows(DuplicatePersonException.class, () -> archivedTaskList.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> archivedTaskList.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInArchivedTaskBook_returnsFalse() {
        assertFalse(archivedTaskList.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInArchivedTaskBook_returnsTrue() {
        archivedTaskList.addTask(ALICE);
        assertTrue(archivedTaskList.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInArchivedTaskBook_returnsTrue() {
        archivedTaskList.addTask(ALICE);
        Task editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertTrue(archivedTaskList.hasTask(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> archivedTaskList.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskList whose tasks list can violate interface constraints.
     */
    private static class ArchivedTaskBookStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ArchivedTaskBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
