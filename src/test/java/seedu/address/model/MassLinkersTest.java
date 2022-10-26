package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTEREST_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalMassLinkers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class MassLinkersTest {

    private final MassLinkers massLinkers = new MassLinkers();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), massLinkers.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> massLinkers.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMassLinkers_replacesData() {
        MassLinkers newData = getTypicalMassLinkers();
        massLinkers.resetData(newData);
        assertEquals(newData, massLinkers);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_NETFLIX)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        MassLinkersStub newData = new MassLinkersStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> massLinkers.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> massLinkers.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInMassLinkers_returnsFalse() {
        assertFalse(massLinkers.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInMassLinkers_returnsTrue() {
        massLinkers.addPerson(ALICE);
        assertTrue(massLinkers.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInMassLinkers_returnsTrue() {
        massLinkers.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_NETFLIX)
                .build();
        assertTrue(massLinkers.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> massLinkers.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyMassLinkers whose persons list can violate interface constraints.
     */
    private static class MassLinkersStub implements ReadOnlyMassLinkers {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Mod> mods = FXCollections.observableArrayList();

        MassLinkersStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
