package jarvis.model;

import static jarvis.logic.commands.CommandTestUtil.VALID_MATRIC_NUM_BOB;
import static jarvis.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.testutil.StudentBuilder;

public class StudentTest {

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

        // same name but different matric num -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withMatricNum(VALID_MATRIC_NUM_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name but same matric num -> returns true
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.equals(editedAlice));
    }
}
