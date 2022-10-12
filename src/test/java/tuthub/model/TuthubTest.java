package tuthub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalPersons.ALICE;
import static tuthub.testutil.TypicalPersons.getTypicalTuthub;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuthub.model.person.Person;
import tuthub.model.person.exceptions.DuplicatePersonException;
import tuthub.testutil.PersonBuilder;

public class TuthubTest {

    private final Tuthub tuthub = new Tuthub();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tuthbu.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuthub.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTuthub_replacesData() {
        Tuthub newData = getTypicalTuthub();
        tuthub.resetData(newData);
        assertEquals(newData, tuthub);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        TuthubStub newData = new TuthubStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> tuthub.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuthub.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTuthub_returnsFalse() {
        assertFalse(tuthub.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTuthub_returnsTrue() {
        tuthub.addPerson(ALICE);
        assertTrue(tuthub.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTuthub_returnsTrue() {
        tuthub.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(tuthub.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tuthub.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTuthub whose persons list can violate interface constraints.
     */
    private static class TuthubStub implements ReadOnlyTuthub {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        TuthubStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
