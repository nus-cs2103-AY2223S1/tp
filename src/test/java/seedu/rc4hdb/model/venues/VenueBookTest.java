package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_BOB_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalVenues.DISCUSSION_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.testutil.VenueBuilder;

public class VenueBookTest {

    private final VenueBook venueBook = new VenueBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), venueBook.getVenueList());
    }

    @Test
    public void resetData_withNullData_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> venueBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyVenueBook_replacesData() {
        VenueBook newData = getTypicalVenueBook();
        venueBook.resetData(newData);
        assertEquals(newData, venueBook);
    }

    @Test
    public void resetData_withDuplicateVenues_throwsDuplicateVenueException() {
        // Two venues with the same identity fields (i.e. venue name)
        Venue editedMeetingRoom = new VenueBuilder()
                .withVenueName(MEETING_ROOM_VENUE_NAME)
                .withBookings(MR_ALICE_MONDAY_6_TO_7PM, MR_BOB_TUESDAY_6_TO_7PM)
                .build();
        List<Venue> newVenues = Arrays.asList(MEETING_ROOM, editedMeetingRoom);
        VenueBookStub newData = new VenueBookStub(newVenues);

        assertThrows(DuplicateVenueException.class, () -> venueBook.resetData(newData));
    }

    @Test
    public void hasVenue_withNullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> venueBook.hasVenue(null));
    }

    @Test
    public void hasVenue_whenVenueNotInVenueBook_returnsFalse() {
        assertFalse(venueBook.hasVenue(MEETING_ROOM));
    }

    @Test
    public void hasVenue_whenVenueInVenueBook_returnsTrue() {
        venueBook.addVenue(DISCUSSION_ROOM);
        assertTrue(venueBook.hasVenue(DISCUSSION_ROOM));
    }

    @Test
    public void hasVenue_onVenueWithSameIdentityFieldsInVenueBook_returnsTrue() {
        venueBook.addVenue(DISCUSSION_ROOM);
        Venue editedDiscussionRoom = new VenueBuilder(DISCUSSION_ROOM)
                .withBookings(MR_ALICE_MONDAY_6_TO_7PM, MR_BOB_TUESDAY_6_TO_7PM)
                .build();
        assertTrue(venueBook.hasVenue(editedDiscussionRoom));
    }

    @Test
    public void getVenueList_thenModifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> venueBook.getVenueList().remove(0));
    }

    /**
     * A stub ReadOnlyVenueBook whose venue list can violate interface constraints.
     */
    private static class VenueBookStub implements ReadOnlyVenueBook {
        private final ObservableList<Venue> venues = FXCollections.observableArrayList();

        VenueBookStub(Collection<Venue> residents) {
            this.venues.setAll(residents);
        }

        @Override
        public ObservableList<Venue> getVenueList() {
            return venues;
        }
    }
}
