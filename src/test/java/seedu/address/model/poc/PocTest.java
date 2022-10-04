package seedu.address.model.poc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalPoc.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PocBuilder;

public class PocTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Poc person = new PocBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePoc() {
        // same object -> returns true
        assertTrue(ALICE.isSamePoc(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePoc(null));

        // same name, all other attributes different -> returns true
        Poc editedAlice = new PocBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePoc(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PocBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePoc(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Poc editedBob = new PocBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePoc(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PocBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePoc(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Poc aliceCopy = new PocBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different company -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Poc editedAlice = new PocBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PocBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PocBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PocBuilder(ALICE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PocBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
