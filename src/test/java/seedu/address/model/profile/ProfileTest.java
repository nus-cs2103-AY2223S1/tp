package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void isSameName() {
        // same object -> returns true
        assertTrue(ALICE.isSameName(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameName(null));

        // same name, all other attributes different -> returns true
        Profile editedAlice = new ProfileBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameName(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ProfileBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameName(editedAlice));

        // name differs in case -> returns true
        Profile editedBob = new ProfileBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameName(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ProfileBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameName(editedBob));
    }

    @Test
    public void isSameEmail() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmail(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEmail(null));

        // same email, all other attributes different -> returns true
        Profile editedAlice = new ProfileBuilder(ALICE).withPhone(VALID_PHONE_BOB).withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEmail(editedAlice));

        // different email, all other attributes same -> returns false
        editedAlice = new ProfileBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameEmail(editedAlice));

        // email differs in case -> returns false
        Profile editedBob = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_BOB.toUpperCase()).build();
        assertFalse(BOB.isSameEmail(editedBob));
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

        // different tags -> returns false
        editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
