package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.ALICE;
import static seedu.intrack.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.intrack.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ALICE.isSameInternship(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameInternship(null));

        // same name, all other attributes different -> returns true
        Internship editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withStatus(VALID_STATUS_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameInternship(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameInternship(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Internship editedBob = new InternshipBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameInternship(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new InternshipBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameInternship(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship aliceCopy = new InternshipBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different internship -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Internship editedAlice = new InternshipBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternshipBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new InternshipBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
