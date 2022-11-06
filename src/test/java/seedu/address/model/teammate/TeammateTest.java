package seedu.address.model.teammate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeammates.ALICE;
import static seedu.address.testutil.TypicalTeammates.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TeammateBuilder;

public class TeammateTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Teammate teammate = new TeammateBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> teammate.getTags().remove(0));
    }

    @Test
    public void isSameTeammate() {
        // same object -> returns true
        assertTrue(ALICE.isSameTeammate(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTeammate(null));

        // same name, all other attributes different -> returns true
        Teammate editedAlice = new TeammateBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTeammate(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TeammateBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTeammate(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Teammate editedBob = new TeammateBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTeammate(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TeammateBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTeammate(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Teammate aliceCopy = new TeammateBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different teammate -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Teammate editedAlice = new TeammateBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TeammateBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new TeammateBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new TeammateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TeammateBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
