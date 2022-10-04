package seedu.condonery.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalPersons.ALICE;
import static seedu.condonery.testutil.TypicalPersons.getTypicalPropertyDirectory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.condonery.model.person.Person;
import seedu.condonery.model.person.exceptions.DuplicatePersonException;
import seedu.condonery.testutil.PersonBuilder;

public class PropertyDirectoryTest {

    private final PropertyDirectory propertyDirectory = new PropertyDirectory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), propertyDirectory.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyDirectory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPropertyDirectory_replacesData() {
        PropertyDirectory newData = getTypicalPropertyDirectory();
        propertyDirectory.resetData(newData);
        assertEquals(newData, propertyDirectory);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        PropertyDirectoryStub newData = new PropertyDirectoryStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> propertyDirectory.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyDirectory.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPropertyDirectory_returnsFalse() {
        assertFalse(propertyDirectory.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPropertyDirectory_returnsTrue() {
        propertyDirectory.addPerson(ALICE);
        assertTrue(propertyDirectory.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPropertyDirectory_returnsTrue() {
        propertyDirectory.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(propertyDirectory.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> propertyDirectory.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyPropertyDirectory whose persons list can violate interface constraints.
     */
    private static class PropertyDirectoryStub implements ReadOnlyPropertyDirectory {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        PropertyDirectoryStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
