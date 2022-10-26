package seedu.classify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_1;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.classify.model.student.Student;
import seedu.classify.model.student.exceptions.DuplicateStudentException;
import seedu.classify.testutil.StudentBuilder;

public class StudentRecordTest {

    private final StudentRecord studentRecord = new StudentRecord();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studentRecord.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentRecord.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStudentRecord_replacesData() {
        StudentRecord newData = getTypicalStudentRecord();
        studentRecord.resetData(newData);
        assertEquals(newData, studentRecord);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withExams(VALID_EXAM_1)
                .build();
        List<Student> newPersons = Arrays.asList(ALICE, editedAlice);
        StudentRecordStub newData = new StudentRecordStub(newPersons);

        assertThrows(DuplicateStudentException.class, () -> studentRecord.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentRecord.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInStudentRecord_returnsFalse() {
        assertFalse(studentRecord.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInStudentRecord_returnsTrue() {
        studentRecord.addStudent(ALICE);
        assertTrue(studentRecord.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInStudentRecord_returnsTrue() {
        studentRecord.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withExams(VALID_EXAM_1)
                .build();
        assertTrue(studentRecord.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studentRecord.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyStudentrecord whose persons list can violate interface constraints.
     */
    private static class StudentRecordStub implements ReadOnlyStudentRecord {
        private final ObservableList<Student> persons = FXCollections.observableArrayList();

        StudentRecordStub(Collection<Student> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return persons;
        }
    }

}
