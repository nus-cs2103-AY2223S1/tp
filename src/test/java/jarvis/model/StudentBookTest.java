package jarvis.model;

import static jarvis.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static jarvis.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalPersons.ALICE;
import static jarvis.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jarvis.model.student.Student;
import org.junit.jupiter.api.Test;

import jarvis.model.student.exceptions.DuplicatePersonException;
import jarvis.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentBookTest {

    private final StudentBook studentBook = new StudentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studentBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        StudentBook newData = getTypicalAddressBook();
        studentBook.resetData(newData);
        assertEquals(newData, studentBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudentBookStub newData = new StudentBookStub(newStudents);

        assertThrows(DuplicatePersonException.class, () -> studentBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentBook.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        assertTrue(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(studentBook.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studentBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class StudentBookStub implements ReadOnlyStudentBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        StudentBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
