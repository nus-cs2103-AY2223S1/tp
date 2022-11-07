package seedu.uninurse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_CONDITION_AMY;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalPatients.ALICE;
import static seedu.uninurse.testutil.TypicalPatients.getTypicalUninurseBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.person.exceptions.DuplicatePersonException;
import seedu.uninurse.testutil.PatientBuilder;

public class UninurseBookTest {

    private final UninurseBook uninurseBook = new UninurseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), uninurseBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uninurseBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUninurseBook_replacesData() {
        UninurseBook newData = getTypicalUninurseBook();
        uninurseBook.resetData(newData);
        assertEquals(newData, uninurseBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PatientBuilder(ALICE).withConditions(VALID_CONDITION_AMY).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        UninurseBookStub newData = new UninurseBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> uninurseBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uninurseBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInUninurseBook_returnsFalse() {
        assertFalse(uninurseBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInUninurseBook_returnsTrue() {
        uninurseBook.addPerson(ALICE);
        assertTrue(uninurseBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInUninurseBook_returnsTrue() {
        uninurseBook.addPerson(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).build();
        assertTrue(uninurseBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uninurseBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyUninurseBook whose persons list can violate interface constraints.
     */
    private static class UninurseBookStub implements ReadOnlyUninurseBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        UninurseBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
