package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.AMY_DIFFERENT_PHONE;
import static seedu.address.testutil.TypicalProfiles.AMY_LOWER_CAPS;
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

        // email differs in case -> returns true
        Profile bob = new ProfileBuilder(BOB).withEmail("bob@u.nus.edu").build();
        Profile editedBob = new ProfileBuilder(BOB).withEmail("BOB@u.nus.edu").build();
        assertTrue(bob.isSameEmail(editedBob));
    }

    @Test
    public void isSamePhone() {
        // same object -> returns true
        assertTrue(ALICE.isSamePhone(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePhone(null));

        // same phone, all other attributes different -> returns true
        Profile editedBob = new ProfileBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(AMY.isSamePhone(editedBob));

        // different phone, all other attributes same -> returns false
        editedBob = new ProfileBuilder(BOB).withPhone(VALID_PHONE_AMY).build();
        assertFalse(BOB.isSamePhone(editedBob));
    }

    @Test
    public void isSameTelegram() {
        // same object -> returns true
        assertTrue(ALICE.isSameTelegram(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTelegram(null));

        // same telegram, all other attributes different -> returns true
        Profile editedBob = new ProfileBuilder(BOB).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(AMY.isSameTelegram(editedBob));

        // different telegram, all other attributes same -> returns false
        editedBob = new ProfileBuilder(BOB).withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(BOB.isSameTelegram(editedBob));
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

    @Test
    public void compareTo_sameNameSamePhone_isEqual() {
        assertEquals(0, AMY.compareTo(AMY));
        assertEquals(0, AMY_DIFFERENT_PHONE.compareTo(AMY_DIFFERENT_PHONE));
    }

    @Test
    public void compareTo_sameNameDifferentPhone_isNotEqual() {
        assertEquals(-1, AMY.compareTo(AMY_DIFFERENT_PHONE));
    }

    @Test
    public void compareTo_differentNameSamePhone_isNotEqual() {
        assertNotEquals(0, AMY.compareTo(AMY_LOWER_CAPS));
    }

    @Test
    public void compareTo_differentNamedifferentPhone_isNotEqual() {
        assertNotEquals(0, AMY.compareTo(ALICE));
    }
}
