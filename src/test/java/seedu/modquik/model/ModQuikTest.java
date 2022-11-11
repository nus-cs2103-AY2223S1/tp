package seedu.modquik.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.modquik.testutil.Assert.assertThrows;
import static seedu.modquik.testutil.TypicalStudents.ALICE;
import static seedu.modquik.testutil.TypicalStudents.getTypicalModQuik;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.exceptions.DuplicateStudentException;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.testutil.StudentBuilder;

public class ModQuikTest {

    private final ModQuik modQuik = new ModQuik();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), modQuik.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modQuik.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModQuik_replacesData() {
        ModQuik newData = getTypicalModQuik();
        modQuik.resetData(newData);
        assertEquals(newData, modQuik);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two persons with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        ModQuikStub newData = new ModQuikStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> modQuik.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modQuik.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInModQuik_returnsFalse() {
        assertFalse(modQuik.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInModQuik_returnsTrue() {
        modQuik.addStudent(ALICE);
        assertTrue(modQuik.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInModQuik_returnsTrue() {
        modQuik.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(modQuik.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modQuik.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyModQuik whose persons list can violate interface constraints.
     */
    private static class ModQuikStub implements ReadOnlyModQuik {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Reminder> reminders = FXCollections.observableArrayList();
        private final ObservableList<Tutorial> tutorials = FXCollections.observableArrayList();
        private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

        ModQuikStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getPersonList() {
            return students;
        }

        @Override
        public ObservableList<Reminder> getReminderList() {
            return reminders;
        }

        @Override
        public ObservableList<Tutorial> getTutorialList() {
            return tutorials;
        }

        @Override
        public ObservableList<Consultation> getConsultationList() {
            return consultations;
        }
    }

}
