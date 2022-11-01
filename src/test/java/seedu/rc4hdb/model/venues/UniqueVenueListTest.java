package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.*;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalVenues.DISCUSSION_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.DISCUSSION_ROOM_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;
import seedu.rc4hdb.testutil.VenueBuilder;

public class UniqueVenueListTest {

    private final UniqueVenueList uniqueVenueList = new UniqueVenueList();
    private final UniqueVenueList emptyUniqueVenueList = new UniqueVenueList();

    /**
     * Returns a booking descriptor with contents equal to MR_ALICE_MONDAY_5_TO_6PM.
     * @return An example booking descriptor for testing purposes
     */
    private BookingDescriptor getExampleBookingDescriptor() {
        BookingDescriptor bookingDescriptor = new BookingDescriptor();
        bookingDescriptor.setResident(ALICE);
        bookingDescriptor.setDayOfWeek(MONDAY);
        bookingDescriptor.setVenueName(MEETING_ROOM_VENUE_NAME);
        bookingDescriptor.setHourPeriod(HP_5_TO_6PM);
        return bookingDescriptor;
    }

    @BeforeEach
    public void initEach() {
        uniqueVenueList.setVenues(emptyUniqueVenueList);
    }

    @Test
    public void contains_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.contains(null));
    }

    @Test
    public void contains_venueNotInList_returnsFalse() {
        assertFalse(uniqueVenueList.contains(DISCUSSION_ROOM));
    }

    @Test
    public void contains_venueInList_returnsTrue() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        assertTrue(uniqueVenueList.contains(DISCUSSION_ROOM));
    }

    @Test
    public void contains_venueWithSameIdentityFieldsInList_returnsTrue() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        Venue editedDiscussionRoom = new VenueBuilder()
                .withVenueName(DISCUSSION_ROOM_NAME)
                .withBookings(MR_ALICE_MONDAY_6_TO_7PM, MR_BOB_TUESDAY_6_TO_7PM)
                .build();
        assertTrue(uniqueVenueList.contains(editedDiscussionRoom));
    }

    @Test
    public void add_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.add(null));
    }

    @Test
    public void add_duplicateVenues_throwsDuplicateVenueException() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        assertThrows(DuplicateVenueException.class, () -> uniqueVenueList.add(DISCUSSION_ROOM));
    }

    // Test cases that involve bookings use a copy of the default venues to avoid modifying the original venues
    // Initially added the static method Venue.copyOf(Venue toCopy) for this but realised that VenueBuilder suffices
    // (even though there is more overhead in using VenueBuilder...)

    @Test
    public void addBooking_withValidBooking_success() {
        uniqueVenueList.add(new VenueBuilder(DISCUSSION_ROOM).build());
        uniqueVenueList.addBooking(DISCUSSION_ROOM_NAME, MR_BOB_TUESDAY_6_TO_7PM);
        assertTrue(uniqueVenueList.getBookings(DISCUSSION_ROOM_NAME).contains(MR_BOB_TUESDAY_6_TO_7PM));
    }

    @Test
    public void addBooking_withInvalidVenueName_throwsVenueNotFoundException() {
        uniqueVenueList.add(new VenueBuilder(HALL).build());
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList
                .addBooking(DISCUSSION_ROOM_NAME, MR_BOB_TUESDAY_6_TO_7PM));
    }

    @Test
    public void addBooking_withNullVenueName_throwsNullPointerException() {
        uniqueVenueList.add(new VenueBuilder(HALL).build());
        assertThrows(NullPointerException.class, () -> uniqueVenueList
                .addBooking(null, MR_BOB_TUESDAY_6_TO_7PM));
    }

    @Test
    public void addBooking_withClashingBooking_throwsBookingClashesException() {
        uniqueVenueList.add(new VenueBuilder(DISCUSSION_ROOM).build());
        uniqueVenueList.addBooking(DISCUSSION_ROOM_NAME, MR_ALICE_MONDAY_6_TO_7PM);
        assertThrows(BookingClashesException.class, () -> uniqueVenueList
                .addBooking(DISCUSSION_ROOM_NAME, MR_ALICE_MONDAY_5_TO_7PM));
    }

    @Test
    public void addBooking_withNullBooking_throwsNullPointerException() {
        uniqueVenueList.add(new VenueBuilder(DISCUSSION_ROOM).build());
        assertThrows(NullPointerException.class, () -> uniqueVenueList
                .addBooking(DISCUSSION_ROOM_NAME, null));
    }

    @Test
    public void removeBooking_withValidBookingDescriptor_success() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        System.out.println(uniqueVenueList.getBookings(MEETING_ROOM_VENUE_NAME).contains(MR_ALICE_MONDAY_5_TO_6PM));
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        uniqueVenueList.removeBooking(getExampleBookingDescriptor());
        assertFalse(uniqueVenueList.getBookings(MEETING_ROOM_VENUE_NAME).contains(MR_ALICE_MONDAY_5_TO_6PM));
    }

    @Test
    public void removeBooking_withNullVenueName_throwsVenueNotFoundException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        BookingDescriptor bookingDescriptor = new BookingDescriptor(getExampleBookingDescriptor());
        bookingDescriptor.setVenueName(null);
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList.removeBooking(bookingDescriptor));
    }

    @Test
    public void removeBooking_withInvalidVenueName_throwsVenueNotFoundException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        BookingDescriptor bookingDescriptor = new BookingDescriptor(getExampleBookingDescriptor());
        bookingDescriptor.setVenueName(DISCUSSION_ROOM_NAME);
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList.removeBooking(bookingDescriptor));
    }

    @Test
    public void removeBooking_bookingNotInVenue_throwsBookingNotFoundException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        assertThrows(BookingNotFoundException.class, () -> uniqueVenueList
                .removeBooking(getExampleBookingDescriptor()));
    }

    @Test
    public void removeBooking_nullBookingDescriptor_throwsNullPointerException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        assertThrows(NullPointerException.class, () -> uniqueVenueList.removeBooking(null));
    }

    @Test
    public void getBookings_withInvalidVenueName_throwsVenueNotFoundException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList.getBookings(DISCUSSION_ROOM_NAME));
    }

    @Test
    public void getBookings_withNullVenueName_throwsNullPointerException() {
        uniqueVenueList.add(new VenueBuilder(MEETING_ROOM).build());
        uniqueVenueList.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        assertThrows(NullPointerException.class, () -> uniqueVenueList.getBookings(null));
    }

    @Test
    public void removeByVenue_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.remove((Venue) null));
    }

    @Test
    public void removeByVenueName_nullVenueName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.remove((VenueName) null));
    }

    @Test
    public void removeByVenue_venueDoesNotExist_throwsVenueNotFoundException() {
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList.remove(DISCUSSION_ROOM));
    }

    @Test
    public void removeByVenueName_venueDoesNotExist_throwsVenueNotFoundException() {
        assertThrows(VenueNotFoundException.class, () -> uniqueVenueList.remove(DISCUSSION_ROOM_NAME));
    }

    @Test
    public void removeByVenue_existingVenue_removesVenue() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        uniqueVenueList.remove(DISCUSSION_ROOM);
        UniqueVenueList expectedUniqueVenueList = new UniqueVenueList();
        assertEquals(expectedUniqueVenueList, uniqueVenueList);
    }

    @Test
    public void removeByVenueName_existingVenue_removesVenue() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        uniqueVenueList.remove(DISCUSSION_ROOM_NAME);
        UniqueVenueList expectedUniqueVenueList = new UniqueVenueList();
        assertEquals(expectedUniqueVenueList, uniqueVenueList);
    }

    @Test
    public void setVenues_withNullUniqueVenueList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.setVenues((UniqueVenueList) null));
    }

    @Test
    public void setVenues_withUniqueVenueList_replacesOwnListWithProvidedUniqueVenueList() {
        uniqueVenueList.add(DISCUSSION_ROOM);
        UniqueVenueList expectedUniqueVenueList = new UniqueVenueList();
        expectedUniqueVenueList.add(HALL);
        uniqueVenueList.setVenues(expectedUniqueVenueList);
        assertEquals(expectedUniqueVenueList, uniqueVenueList);
    }

    @Test
    public void setVenues_withNullVenues_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueVenueList.setVenues(Arrays.asList(DISCUSSION_ROOM, null)));
    }

    @Test
    public void setVenues_listWithDuplicateVenues_throwsDuplicateVenueException() {
        assertThrows(DuplicateVenueException.class, () ->
                uniqueVenueList.setVenues(Arrays.asList(DISCUSSION_ROOM, DISCUSSION_ROOM)));
    }

    @Test
    public void setVenues_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVenueList.setVenues((List<Venue>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueVenueList.asUnmodifiableObservableList().remove(0));
    }

}
