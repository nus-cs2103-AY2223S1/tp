package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalProfNus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ProfNusTest {

    private final ProfNus profNus = new ProfNus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), profNus.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> profNus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyProfNus_replacesData() {
        ProfNus newData = getTypicalProfNus();
        profNus.resetData(newData);
        assertEquals(newData, profNus);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ProfNusStub newData = new ProfNusStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> profNus.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> profNus.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInProfNus_returnsFalse() {
        assertFalse(profNus.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInProfNus_returnsTrue() {
        profNus.addPerson(ALICE);
        assertTrue(profNus.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInProfNus_returnsTrue() {
        profNus.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(profNus.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> profNus.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyProfNus whose persons list can violate interface constraints.
     */
    private static class ProfNusStub implements ReadOnlyProfNus {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Student> tutors = FXCollections.observableArrayList();

        ProfNusStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Student> getTutorList() {
            return tutors;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Schedule> getScheduleList() {
            return FXCollections.observableArrayList();
        }
    }

}
