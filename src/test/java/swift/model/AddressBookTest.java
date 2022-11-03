package swift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static swift.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static swift.testutil.Assert.assertThrows;
import static swift.testutil.TypicalPersons.ALICE;
import static swift.testutil.TypicalPersons.getTypicalAddressBook;
import static swift.testutil.TypicalTasks.BUY_MILK;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.person.exceptions.DuplicatePersonException;
import swift.model.task.Task;
import swift.model.task.exceptions.DuplicateTaskException;
import swift.testutil.PersonBuilder;
import swift.testutil.TypicalBridges;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }
    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
            .withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, Arrays.asList(), Arrays.asList());
        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }
    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }
    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
            .withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicateTask_throwsDuplicateTaskException() {
        List<Task> newTasks = Arrays.asList(BUY_MILK, BUY_MILK);
        AddressBookStub newData = new AddressBookStub(Arrays.asList(), newTasks, Arrays.asList());
        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTask(BUY_MILK));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        addressBook.addTask(BUY_MILK);
        assertTrue(addressBook.hasTask(BUY_MILK));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0)
        );
    }

    @Test
    public void hasBridge_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasBridge(null));
    }

    @Test
    public void hasBridge_bridgeNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasBridge(TypicalBridges.DEFAULT_BRIDGE_1));
    }
    @Test
    public void hasBridge_bridgeInAddressBook_returnsTrue() {
        addressBook.addBridge(TypicalBridges.DEFAULT_BRIDGE_1);
        assertTrue(addressBook.hasBridge(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void getBridgeList_modifyBridge_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, () -> addressBook.getBridgeList().remove(0)
        );
    }

    /**
    * A stub ReadOnlyAddressBook whose persons/tasks list can violate interface constraints.
    */
    private static class AddressBookStub implements ReadOnlyAddressBook {

        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<PersonTaskBridge> bridges = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Task> tasks, Collection<PersonTaskBridge> bridges) {
            this.persons.setAll(persons);
            this.tasks.setAll(tasks);
            this.bridges.setAll(bridges);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<PersonTaskBridge> getBridgeList() {
            return bridges;
        }
    }
}
