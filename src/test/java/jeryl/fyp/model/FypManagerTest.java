package jeryl.fyp.model;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.BENSON;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
import static jeryl.fyp.testutil.TypicalStudents.DANIEL;
import static jeryl.fyp.testutil.TypicalStudents.ELLE;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jeryl.fyp.model.student.DeadlineList;
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

    @Test
    public void getUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            fypManager.addStudent(student);
        }
        assertEquals(fypManager.getUncompletedStudentList().get(0), ALICE);
        assertEquals(fypManager.getUncompletedStudentList().get(1), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> fypManager.getUncompletedStudentList().get(2));
    }

    @Test
    public void getCompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            fypManager.addStudent(student);
        }
        assertEquals(fypManager.getCompletedStudentList().get(0), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> fypManager.getCompletedStudentList().get(1));
    }

    @Test
    public void getSortedByProjectNameUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, CARL, DANIEL };
        for (Student student : students) {
            fypManager.addStudent(student);
        }
        assertEquals(fypManager.getSortedByProjectNameUncompletedStudentList().get(0), CARL);
        assertEquals(fypManager.getSortedByProjectNameUncompletedStudentList().get(1), BENSON);
        assertEquals(fypManager.getSortedByProjectNameUncompletedStudentList().get(2), ALICE);
        assertThrows(IndexOutOfBoundsException.class, () -> fypManager
                .getSortedByProjectNameUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedByProjectStatusUncompletedStudentList() {
        Student[] students = { CARL, BENSON, DANIEL, ALICE };
        for (Student student : students) {
            fypManager.addStudent(student);
        }
        assertEquals(fypManager.getSortedByProjectStatusUncompletedStudentList().get(0), CARL);
        assertEquals(fypManager.getSortedByProjectStatusUncompletedStudentList().get(1), ALICE);
        assertEquals(fypManager.getSortedByProjectStatusUncompletedStudentList().get(2), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> fypManager
                .getSortedByProjectStatusUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedCompletedStudentList() {
        Student[] students = { BENSON, ALICE, DANIEL, ELLE };
        for (Student student : students) {
            fypManager.addStudent(student);
        }
        assertEquals(fypManager.getSortedCompletedStudentList().get(0), ELLE);
        assertEquals(fypManager.getSortedCompletedStudentList().get(1), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> fypManager.getSortedCompletedStudentList().get(2));
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

        @Override
        public ObservableList<Student> getUncompletedStudentList() {
            return students.filtered(student -> !student.getProjectStatus().projectStatus.equals("DONE"));
        }

        @Override
        public ObservableList<Student> getCompletedStudentList() {
            return students.filtered(student -> student.getProjectStatus().projectStatus.equals("DONE"));
        }

        @Override
        public ObservableList<Student> getSortedByProjectNameUncompletedStudentList() {
            return getUncompletedStudentList().sorted(Comparator.comparing(s -> s.getProjectName().toString()
                    .toLowerCase()));
        }

        @Override
        public ObservableList<Student> getSortedByProjectStatusUncompletedStudentList() {
            return getUncompletedStudentList().sorted((a, b) -> {
                int statusComp = b.getProjectStatus().toString().toLowerCase()
                        .compareTo(a.getProjectStatus().toString().toLowerCase());

                if (statusComp != 0) {
                    return statusComp;
                }

                return a.getProjectName().toString().toLowerCase()
                        .compareTo(b.getProjectName().toString().toLowerCase());
            });
        }

        @Override
        public ObservableList<Student> getSortedCompletedStudentList() {
            return getCompletedStudentList().sorted(Comparator.comparing(s -> s.getProjectName().toString()
                    .toLowerCase()));
        }

        @Override
        public DeadlineList getDeadlineList(Student student) {
            return student.getDeadlineList();
        }
    }

}
