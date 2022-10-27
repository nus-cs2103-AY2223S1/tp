package taskbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import taskbook.model.person.Person;
import taskbook.model.person.exceptions.DuplicatePersonException;
import taskbook.model.task.Task;
import taskbook.testutil.Assert;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TypicalTaskBook;

public class TaskBookTest {

    private final TaskBook taskBook = new TaskBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskBook_replacesData() {
        TaskBook newData = TypicalTaskBook.getTypicalTaskBook();
        taskBook.resetData(newData);
        assertEquals(newData, taskBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(TypicalTaskBook.ALICE, editedAlice);
        TaskBookStub newData = new TaskBookStub(newPersons);

        Assert.assertThrows(DuplicatePersonException.class, () -> taskBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTaskBook_returnsFalse() {
        assertFalse(taskBook.hasPerson(TypicalTaskBook.ALICE));
    }

    @Test
    public void hasPerson_personInTaskBook_returnsTrue() {
        taskBook.addPerson(TypicalTaskBook.ALICE);
        assertTrue(taskBook.hasPerson(TypicalTaskBook.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTaskBook_returnsTrue() {
        taskBook.addPerson(TypicalTaskBook.ALICE);
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(taskBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> taskBook.getPersonList().remove(0));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> taskBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskBook whose persons list can violate interface constraints.
     */
    private static class TaskBookStub implements ReadOnlyTaskBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
