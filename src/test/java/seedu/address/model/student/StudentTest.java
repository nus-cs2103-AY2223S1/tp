package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
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
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.hasSameNameOrId(editedAlice));

        // different name, different id, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentName(VALID_STUDENT_NAME_BOB)
                .withId(VALID_ID_BOB).build();
        assertFalse(ALICE.hasSameNameOrId(editedAlice));

        // name differs in case, different id, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(BOB).withStudentName(VALID_STUDENT_NAME_BOB.toLowerCase())
                .withId(VALID_ID_AMY).build();
        assertFalse(BOB.hasSameNameOrId(editedBob));

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

        // different address -> returns false
        editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
