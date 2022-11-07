package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERMEDIATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalTeachersPet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class TeachersPetTest {

    private final TeachersPet teachersPet = new TeachersPet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teachersPet.getStudentList());
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
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERMEDIATE)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        TeachersPetStub newData = new TeachersPetStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> teachersPet.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachersPet.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTeachersPet_returnsFalse() {
        assertFalse(teachersPet.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTeachersPet_returnsTrue() {
        teachersPet.addStudent(ALICE);
        assertTrue(teachersPet.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInTeachersPet_returnsTrue() {
        teachersPet.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERMEDIATE)
                .build();
        assertTrue(teachersPet.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teachersPet.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyTeachersPet whose students list can violate interface constraints.
     */
    private static class TeachersPetStub implements ReadOnlyTeachersPet {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Student> schedule = FXCollections.observableArrayList();
        TeachersPetStub(Collection<Student> students) {
            this.students.setAll(students);
            this.schedule.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Student> getScheduleList() {
            return schedule;
        }

        @Override
        public void sortStudents(Comparator<Student> comparator) {}
    }

}
