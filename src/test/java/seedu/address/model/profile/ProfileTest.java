package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProfileBuilder;

public class ProfileTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Profile profile = new ProfileBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> profile.getTags().remove(0));
    }

    @Test
    public void isSameProfile() {
        // same object -> returns true
        assertTrue(ALICE.isSameProfile(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameProfile(null));

        // same name, all other attributes different -> returns true
        Profile editedAlice = new ProfileBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameProfile(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ProfileBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameProfile(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Profile editedBob = new ProfileBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameProfile(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ProfileBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameProfile(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Profile aliceCopy = new ProfileBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different profile -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Profile editedAlice = new ProfileBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ProfileBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ProfileBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ProfileBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
