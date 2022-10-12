package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.testutil.TypicalGuests.ALICE;
import static seedu.guest.testutil.TypicalGuests.BOB;

import org.junit.jupiter.api.Test;

import seedu.guest.testutil.GuestBuilder;

public class GuestTest {

    @Test
    public void isSameGuest() {
        // same object -> returns true
        assertTrue(ALICE.isSameGuest(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameGuest(null));

        // same name, all other attributes different -> returns true
        Guest editedAlice = new GuestBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSameGuest(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameGuest(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Guest editedBob = new GuestBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameGuest(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new GuestBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameGuest(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Guest aliceCopy = new GuestBuilder(ALICE).build();
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
        Guest editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new GuestBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new GuestBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date range -> returns false
        editedAlice = new GuestBuilder(ALICE).withDateRange(VALID_DATE_RANGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different number of guests -> returns false
        editedAlice = new GuestBuilder(ALICE).withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashcode() {
        // same values -> returns true
        Guest aliceCopy = new GuestBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // same object -> returns true
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // different person -> returns false
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        // different name -> returns false
        Guest editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different phone -> returns false
        editedAlice = new GuestBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> returns false
        editedAlice = new GuestBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different date range -> returns false
        editedAlice = new GuestBuilder(ALICE).withDateRange(VALID_DATE_RANGE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> returns false
        editedAlice = new GuestBuilder(ALICE).withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());
    }
}
