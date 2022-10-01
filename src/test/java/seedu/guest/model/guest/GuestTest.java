package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalPersons.ALICE;
import static seedu.guest.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.guest.testutil.PersonBuilder;

public class GuestTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Guest guest = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> guest.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameGuest(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameGuest(null));

        // same name, all other attributes different -> returns true
        Guest editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameGuest(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameGuest(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Guest editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameGuest(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameGuest(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Guest aliceCopy = new PersonBuilder(ALICE).build();
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
        Guest editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
