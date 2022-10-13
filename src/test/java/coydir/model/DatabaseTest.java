package coydir.model;

import static coydir.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static coydir.testutil.Assert.assertThrows;
import static coydir.testutil.TypicalPersons.ALICE;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import coydir.model.person.Person;
import coydir.model.person.exceptions.DuplicatePersonException;
import coydir.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseTest {

    private final Database database = new Database();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), database.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Database newData = getTypicalDatabase();
        database.resetData(newData);
        assertEquals(newData, database);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        DatabaseStub newData = new DatabaseStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> database.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(database.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        database.addPerson(ALICE);
        assertTrue(database.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        database.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(database.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class DatabaseStub implements ReadOnlyDatabase {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        DatabaseStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
