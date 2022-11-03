package seedu.taassist.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BOB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getModuleDataList().remove(0));
    }

    @Test
    public void addModuleClass_moduleClassExists_returnsSame() {
        Student student = new StudentBuilder().withModuleClasses(CS1231S).build();
        Student updatedStudent = student.addModuleClass(CS1231S);
        assertTrue(student.equals(updatedStudent));
    }

    @Test
    public void addModuleClass_moduleClassDoesNotExist_addsModuleClass() {
        Student student = new StudentBuilder().withModuleClasses().build();
        Student updatedStudent = student.addModuleClass(CS1231S);
        Student expectedStudent = new StudentBuilder().withModuleClasses(CS1231S).build();
        assertTrue(updatedStudent.equals(expectedStudent));
    }

    @Test
    public void updateGrade_gradeDoesntExist_givesNewGrade() {
        Student student = ALICE.addModuleClass(CS1231S).updateGrade(CS1231S, LAB_1, 100);

        SessionData sessionData = new SessionData(LAB_1, 100);
        StudentModuleData moduleData = new StudentModuleData(CS1231S, List.of(sessionData));
        Student expectedStudent = new StudentBuilder(ALICE).withModuleData(List.of(moduleData)).build();

        assertTrue(student.equals(expectedStudent));
    }

    @Test
    public void updateGrade_gradeExists_updatesGrade() {
        Student student = ALICE.addModuleClass(CS1231S)
                .updateGrade(CS1231S, LAB_1, 100)
                .updateGrade(CS1231S, LAB_1, 50);

        SessionData sessionData = new SessionData(LAB_1, 50);
        StudentModuleData moduleData = new StudentModuleData(CS1231S, List.of(sessionData));
        Student expectedStudent = new StudentBuilder(ALICE).withModuleData(List.of(moduleData)).build();

        assertTrue(student.equals(expectedStudent));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSame(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSame(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withModuleClasses(new ModuleClass(VALID_CLASS_CS1101S)).build();
        assertTrue(ALICE.isSame(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSame(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSame(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSame(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different classes -> returns false
        editedAlice = new StudentBuilder(ALICE).withModuleClasses(new ModuleClass(VALID_CLASS_CS1101S)).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
