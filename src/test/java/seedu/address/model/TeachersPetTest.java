package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachersPet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Person;
import seedu.address.model.student.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class TeachersPetTest {

    private final TeachersPet teachersPet = new TeachersPet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teachersPet.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachersPet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTeachersPet_replacesData() {
        TeachersPet newData = getTypicalTeachersPet();
        teachersPet.resetData(newData);
        assertEquals(newData, teachersPet);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        TeachersPetStub newData = new TeachersPetStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> teachersPet.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachersPet.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTeachersPet_returnsFalse() {
        assertFalse(teachersPet.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTeachersPet_returnsTrue() {
        teachersPet.addPerson(ALICE);
        assertTrue(teachersPet.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTeachersPet_returnsTrue() {
        teachersPet.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(teachersPet.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teachersPet.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTeachersPet whose persons list can violate interface constraints.
     */
    private static class TeachersPetStub implements ReadOnlyTeachersPet {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Person> schedule = FXCollections.observableArrayList();
        TeachersPetStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            this.schedule.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Person> getScheduleList() {
            return schedule;
        }

        @Override
        public void sortPersons(Comparator<Person> comparator) {}
    }

}
