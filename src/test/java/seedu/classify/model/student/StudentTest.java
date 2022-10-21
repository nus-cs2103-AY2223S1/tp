package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_CLASS_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.classify.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getExams().remove(0));
    }

    @Test
    public void hasSameNameOrId() {
        // same object -> returns true
        assertTrue(ALICE.hasSameNameOrId(ALICE));

        // null -> returns false
        assertFalse(ALICE.hasSameNameOrId(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE)
                .withId(VALID_ID_BOB)
                .withClassName(VALID_CLASS_BOB)
                .withParentName(VALID_PARENT_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withExams(VALID_EXAM_1).build();
        assertTrue(ALICE.hasSameNameOrId(editedAlice));

        // different name, different id, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentName(VALID_STUDENT_NAME_BOB)
                .withId(VALID_ID_BOB).build();
        assertFalse(ALICE.hasSameNameOrId(editedAlice));

        // name differs in case, different id, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB).withStudentName(VALID_STUDENT_NAME_BOB.toLowerCase())
                .withId(VALID_ID_AMY).build();
        assertTrue(BOB.hasSameNameOrId(editedBob));

        // name has trailing spaces, different id, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_STUDENT_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withStudentName(nameWithTrailingSpaces)
                .withId(VALID_ID_AMY).build();
        assertFalse(BOB.hasSameNameOrId(editedBob));
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

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withExams(VALID_EXAM_1).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
