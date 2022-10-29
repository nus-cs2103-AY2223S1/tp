package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getInterests().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        //different object, with same name and same attribute -> returns true
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, all other attributes different -> returns false
        editedAlice = new StudentBuilder(ALICE)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withGitHub(VALID_GITHUB_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withInterests(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_SWE)
                .build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new StudentBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB)
                .withName(VALID_NAME_BOB.toLowerCase())
                .build();
        assertTrue(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB)
                .withName(nameWithTrailingSpaces)
                .build();
        assertTrue(BOB.isSameStudent(editedBob));
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
        editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different interests -> returns false
        editedAlice = new StudentBuilder(ALICE).withInterests(VALID_INTEREST_SWE).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
