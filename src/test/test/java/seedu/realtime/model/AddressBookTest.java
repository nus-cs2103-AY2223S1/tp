package seedu.realtime.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalPersons.ALICE;
import static seedu.realtime.testutil.TypicalPersons.getTypicalRealTime;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.realtime.model.person.Person;
import seedu.realtime.model.person.exceptions.DuplicatePersonException;
import seedu.realtime.testutil.PersonBuilder;

public class RealTimeTest {

    private final realTime realTime = new realTime();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), realTime.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realTime.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRealTime_replacesData() {
        realTime newData = getTypicalRealTime();
        realTime.resetData(newData);
        assertEquals(newData, realTime);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        RealTimeStub newData = new RealTimeStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> realTime.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realTime.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInRealTime_returnsFalse() {
        assertFalse(realTime.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInRealTime_returnsTrue() {
        realTime.addPerson(ALICE);
        assertTrue(realTime.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInRealTime_returnsTrue() {
        realTime.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(realTime.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> realTime.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyRealTime whose persons list can violate interface constraints.
     */
    private static class RealTimeStub implements ReadOnlyRealTime {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        RealTimeStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
