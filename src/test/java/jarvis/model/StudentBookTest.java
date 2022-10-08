package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.DuplicateStudentException;
import jarvis.testutil.StudentBuilder;
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
    public void resetData_withValidReadOnlyStudentBook_replacesData() {
        StudentBook newData = getTypicalStudentBook();
        studentBook.resetData(newData);
        assertEquals(newData, studentBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudentBookStub newData = new StudentBookStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> studentBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentBook_returnsFalse() {
        assertFalse(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudentBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        assertTrue(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInStudentBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(studentBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studentBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyStudentBook whose students list can violate interface constraints.
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
