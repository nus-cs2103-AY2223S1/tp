package seedu.guest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalGuests.ALICE;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.exceptions.DuplicateGuestException;
import seedu.guest.testutil.GuestBuilder;

public class GuestBookTest {

    private final GuestBook guestBook = new GuestBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), guestBook.getGuestList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGuestBook_replacesData() {
        GuestBook newData = getTypicalGuestBook();
        guestBook.resetData(newData);
        assertEquals(newData, guestBook);
    }

    @Test
    public void resetData_withDuplicateGuests_throwsDuplicateGuestException() {
        // Two persons with the same identity fields
        Guest editedAlice = new GuestBuilder(ALICE).build();
        List<Guest> newGuests = Arrays.asList(ALICE, editedAlice);
        GuestBookStub newData = new GuestBookStub(newGuests);

        assertThrows(DuplicateGuestException.class, () -> guestBook.resetData(newData));
    }

    @Test
    public void hasGuest_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.hasGuest(null));
    }

    @Test
    public void hasGuest_guestNotInGuestBook_returnsFalse() {
        assertFalse(guestBook.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestInGuestBook_returnsTrue() {
        guestBook.addGuest(ALICE);
        assertTrue(guestBook.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestWithSameIdentityFieldsInGuestBook_returnsTrue() {
        guestBook.addGuest(ALICE);
        Guest editedAlice = new GuestBuilder(ALICE).build();
        assertTrue(guestBook.hasGuest(editedAlice));
    }

    @Test
    public void getGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> guestBook.getGuestList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class GuestBookStub implements ReadOnlyGuestBook {
        private final ObservableList<Guest> guests = FXCollections.observableArrayList();

        GuestBookStub(Collection<Guest> guests) {
            this.guests.setAll(guests);
        }

        @Override
        public ObservableList<Guest> getGuestList() {
            return guests;
        }
    }

    @Test
    public void hashcode() {
        // same values -> return true
        assertEquals(guestBook.hashCode(), new GuestBook().hashCode());

        // different values -> return false
        guestBook.addGuest(ALICE);
        assertNotEquals(guestBook.hashCode(), new GuestBook().hashCode());
    }
}
