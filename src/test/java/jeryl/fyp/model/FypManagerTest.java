package jeryl.fyp.model;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.exceptions.DuplicateStudentException;
import jeryl.fyp.testutil.StudentBuilder;

public class FypManagerTest {

    private final FypManager fypManager = new FypManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fypManager.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fypManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFypManager_replacesData() {
        FypManager newData = getTypicalFypManager();
        fypManager.resetData(newData);
        assertEquals(newData, fypManager);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        FypManagerStub newData = new FypManagerStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> fypManager.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fypManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInFypManager_returnsFalse() {
        assertFalse(fypManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInFypManager_returnsTrue() {
        fypManager.addStudent(ALICE);
        assertTrue(fypManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInFypManager_returnsTrue() {
        fypManager.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(fypManager.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fypManager.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyFypManager whose students list can violate interface constraints.
     */
    private static class FypManagerStub implements ReadOnlyFypManager {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        FypManagerStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
