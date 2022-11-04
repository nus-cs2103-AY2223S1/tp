package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalSurvin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class SurvinTest {

    private final Survin survin = new Survin();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), survin.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> survin.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySurvin_replacesData() {
        Survin newData = getTypicalSurvin();
        survin.resetData(newData);
        assertEquals(newData, survin);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        SurvinStub newData = new SurvinStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> survin.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> survin.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInSurvin_returnsFalse() {
        assertFalse(survin.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInSurvin_returnsTrue() {
        survin.addPerson(ALICE);
        assertTrue(survin.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInSurvin_returnsTrue() {
        survin.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(survin.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> survin.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlySurvin whose persons list can violate interface constraints.
     */
    private static class SurvinStub implements ReadOnlySurvin {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        SurvinStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
