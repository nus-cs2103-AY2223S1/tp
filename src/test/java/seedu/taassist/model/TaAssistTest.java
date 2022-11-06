package seedu.taassist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.exceptions.DuplicateElementException;
import seedu.taassist.testutil.StudentBuilder;

public class TaAssistTest {

    private final TaAssist taAssist = new TaAssist();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taAssist.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taAssist.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaAssist_replacesData() {
        TaAssist newData = getTypicalTaAssist();
        taAssist.resetData(newData);
        assertEquals(newData, taAssist);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withModuleClasses(new ModuleClass(VALID_CLASS_CS1101S))
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        List<ModuleClass> newModuleClasses = List.of();
        TaAssistStub newData = new TaAssistStub(newStudents, newModuleClasses);

        assertThrows(DuplicateElementException.class, () -> taAssist.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taAssist.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTaAssist_returnsFalse() {
        assertFalse(taAssist.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTaAssist_returnsTrue() {
        taAssist.addStudent(ALICE);
        assertTrue(taAssist.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInTaAssist_returnsTrue() {
        taAssist.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withModuleClasses(new ModuleClass(VALID_CLASS_CS1101S))
                .build();
        assertTrue(taAssist.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taAssist.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyTaAssist whose students list can violate interface constraints.
     */
    private static class TaAssistStub implements ReadOnlyTaAssist {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<ModuleClass> moduleClasses = FXCollections.observableArrayList();

        TaAssistStub(Collection<Student> students, Collection<ModuleClass> moduleClasses) {
            this.students.setAll(students);
            this.moduleClasses.setAll(moduleClasses);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return moduleClasses;
        }
    }

}
