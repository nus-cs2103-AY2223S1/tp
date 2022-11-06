package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalArchivedTaskBook;

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

public class ArchivedTaskBookTest {

    private final ArchivedTaskBook archivedTaskBook = new ArchivedTaskBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), archivedTaskBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> archivedTaskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyArchivedTaskBook_replacesData() {
        ArchivedTaskBook newData = getTypicalArchivedTaskBook();
        archivedTaskBook.resetData(newData);
        assertEquals(newData, archivedTaskBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicatePersonException() {
        // Two tasks with the same identity fields
        Task editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        ArchivedTaskBookTest.ArchivedTaskBookStub newData = new ArchivedTaskBookTest.ArchivedTaskBookStub(newTasks);

        assertThrows(DuplicatePersonException.class, () -> archivedTaskBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> archivedTaskBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInArchivedTaskBook_returnsFalse() {
        assertFalse(archivedTaskBook.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInArchivedTaskBook_returnsTrue() {
        archivedTaskBook.addTask(ALICE);
        assertTrue(archivedTaskBook.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInArchivedTaskBook_returnsTrue() {
        archivedTaskBook.addTask(ALICE);
        Task editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertTrue(archivedTaskBook.hasTask(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> archivedTaskBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose tasks list can violate interface constraints.
     */
    private static class ArchivedTaskBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ArchivedTaskBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getPersonList() {
            return tasks;
        }
    }

}
