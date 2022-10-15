package seedu.studmap.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalStudents.ALICE;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.exceptions.DuplicateStudentException;
import seedu.studmap.testutil.StudentBuilder;

public class StudMapTest {

    private final StudMap studMap = new StudMap();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studMap.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studMap.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStudMap_replacesData() {
        StudMap newData = getTypicalStudMap();
        studMap.resetData(newData);
        assertEquals(newData, studMap);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicatestudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudMapStub newData = new StudMapStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> studMap.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studMap.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudMap_returnsFalse() {
        assertFalse(studMap.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudMap_returnsTrue() {
        studMap.addStudent(ALICE);
        assertTrue(studMap.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInStudMap_returnsTrue() {
        studMap.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(studMap.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studMap.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyStudMap whose students list can violate interface constraints.
     */
    private static class StudMapStub implements ReadOnlyStudMap {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        StudMapStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
