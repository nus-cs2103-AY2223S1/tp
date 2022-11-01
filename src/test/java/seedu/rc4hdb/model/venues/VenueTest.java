package seedu.rc4hdb.model.venues;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import static seedu.rc4hdb.testutil.TypicalBookings.*;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalVenues.*;
import seedu.rc4hdb.testutil.VenueBuilder;

public class VenueTest {

    private final BookingDescriptor aliceBookingDescriptor = getBookingDescriptorOf(MR_ALICE_MONDAY_5_TO_6PM);

    // Use the constructor which initialises a Venue with an empty booking list
    private final Venue emptyVenue = new Venue(MEETING_ROOM_VENUE_NAME);

    // Test venue will always have the MR_ALICE_MONDAY_5_TO_6PM booking
    private Venue testVenue = new VenueBuilder(MEETING_ROOM)
            .withBookings(MR_ALICE_MONDAY_5_TO_6PM)
            .build();

    // Note: Venue does not have a method for setting the booking list, hence @BeforeEach can only be used to
    // instantiate and reset the (reusable) testVenue variable with a new Venue instance before every test case.

    // One alternative to reduce this overhead is to do away with @BeforeEach altogether, and instead
    // perform the complementary operation within each test case. For example, making sure to remove a booking
    // after adding it to testVenue in a particular test case.

    // However, to avoid the possibility of breaking the entire set of tests with just one errand test case,
    // we have decided to re-instantiate a new Venue each time.

    // Some checks are enforced to avoid re-instantiating a new Venue instance for a correct testVenue

    @BeforeEach
    public void initEach() {
        if (isDefaultTestVenue(testVenue)) {
            return;
        }
        testVenue = new VenueBuilder(MEETING_ROOM)
                .withBookings(MR_ALICE_MONDAY_5_TO_6PM)
                .build();
    }

    private boolean isDefaultTestVenue(Venue someVenue) {
        if (someVenue.getVenueName() != MEETING_ROOM_VENUE_NAME) {
            return false;
        }
        if (!someVenue.getObservableBookings().equals(List.of(MR_ALICE_MONDAY_5_TO_6PM))) {
            return false;
        }
        return true;
    }

    private BookingDescriptor getBookingDescriptorOf(Booking booking) {
        BookingDescriptor descriptor = new BookingDescriptor();
        descriptor.setVenueName(booking.getVenueName());
        descriptor.setResident(booking.getResident());
        descriptor.setHourPeriod(booking.getHourPeriod());
        descriptor.setDayOfWeek(booking.getDayOfWeek());
        return descriptor;
    }

    // TODO: Should add requireNonNull checks for Venue class
    // TODO: Update the equals method for the Venue class

    // Test the first constructor
    @Test
    public void constructor_withoutListOfBookings_returnsVenueWithNoBookings() {
        Venue venue = new Venue(MEETING_ROOM_VENUE_NAME);
        assertEquals(venue.getObservableBookings(), Collections.emptyList());
    }

    // Test the second constructor
    @Test
    public void constructor_withListOfBookings_returnsVenueWithSameListOfBookings() {
        List<Booking> bookings = Arrays.asList(MR_ALICE_MONDAY_5_TO_6PM, MR_BOB_TUESDAY_6_TO_7PM);
        Venue venue = new Venue(bookings, MEETING_ROOM_VENUE_NAME);
        assertEquals(venue.getObservableBookings(), bookings);
    }

    // Null input for first constructor throws NullPointerException
    @Test
    public void constructor_withNullVenueName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    // At least one null input for second constructor throws NullPointerException
    @Test
    public void constructor_withNullVenueNameOrNullBookings_throwsNullPointerException() {
        List<Booking> bookings = Arrays.asList(MR_ALICE_MONDAY_5_TO_6PM, MR_BOB_TUESDAY_6_TO_7PM);
        assertThrows(NullPointerException.class, () -> new Venue(bookings, null));
        assertThrows(NullPointerException.class, () -> new Venue(null, MEETING_ROOM_VENUE_NAME));
        assertThrows(NullPointerException.class, () -> new Venue(null, null));
    }

    // Add valid booking results in success
    @Test
    public void addBooking_validBooking_success() {
        Venue expectedVenue = new VenueBuilder(MEETING_ROOM)
                .withBookings(MR_ALICE_MONDAY_5_TO_6PM, MR_BOB_TUESDAY_6_TO_7PM)
                .build();
        testVenue.addBooking(MR_BOB_TUESDAY_6_TO_7PM);
        assertEquals(testVenue, expectedVenue);
    }

    // Add existing bookings throws BookingClashesException
    @Test
    public void addBooking_existingBooking_throwsBookingClashesException() {
        assertThrows(BookingClashesException.class, () ->
                testVenue.addBooking(MR_ALICE_MONDAY_5_TO_6PM));
    }

    // Add clashing bookings throws BookingClashesException
    @Test
    public void addBooking_clashingBooking_throwsBookingClashesException() {
        assertThrows(BookingClashesException.class, () ->
                testVenue.addBooking(MR_ALICE_MONDAY_5_TO_7PM));
    }

    // Add null booking throws NullPointerException
    @Test
    public void addBooking_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testVenue.addBooking(null));
    }

    // Remove booking with null booking descriptor throws NullPointerException
    @Test
    public void removeBooking_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testVenue.removeBooking(null));
    }

    // hasClashes with null booking throws NullPointerException
    @Test
    public void hasClashes_withNullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testVenue.hasClashes(null));
    }

    // Venue with empty booking list does not have clashes with booking
    @Test
    public void hasClashes_emptyVenueComparedWithTypicalBookings_returnsFalse() {
        assertFalse(emptyVenue.hasClashes(MR_ALICE_MONDAY_5_TO_6PM));
        assertFalse(emptyVenue.hasClashes(MR_ALICE_MONDAY_6_TO_7PM));
        assertFalse(emptyVenue.hasClashes(MR_ALICE_MONDAY_5_TO_7PM));
        assertFalse(emptyVenue.hasClashes(MR_ALICE_TUESDAY_6_TO_7PM));
        assertFalse(emptyVenue.hasClashes(MR_BOB_TUESDAY_6_TO_7PM));
    }

    // Venue with valid booking results in venue having clashes with overlapping booking
    @Test
    public void hasClashes_nonEmptyVenueComparedWithOverlappingBooking_returnsTrue() {
        assertTrue(testVenue.hasClashes(MR_ALICE_MONDAY_5_TO_7PM));
    }

    // Remove valid booking results in success
    @Test
    public void removeBooking_someBookingInVenue_success() {
        testVenue.removeBooking(aliceBookingDescriptor);
        assertEquals(testVenue, emptyVenue);
    }

    // Remove non-existent booking throws BookingNotFoundException
    @Test
    public void removeBooking_nonExistentBooking_throwsBookingNotFoundException() {
        assertThrows(BookingNotFoundException.class, () ->
                emptyVenue.removeBooking(aliceBookingDescriptor));
    }

    // Venues with different venue names are not equal
    @Test
    public void equals_twoVenuesWithDifferentVenueNames_isNotEqual() {
        // Both venues have the an (empty) booking list, and only differ by venue name.
        Venue otherVenue = new Venue(DISCUSSION_ROOM_NAME);
        assertNotEquals(emptyVenue, otherVenue);
    }

    // Create venues with the same venue name but different booking order in booking list are equal
    @Test
    public void equals_twoVenuesWithSameBookingsButDifferentBookingOrderInList_isEqual() {
        Venue otherVenue = new VenueBuilder(MEETING_ROOM)
                .withBookings(MR_BOB_TUESDAY_6_TO_7PM, MR_ALICE_MONDAY_5_TO_6PM)
                .build();
        testVenue.addBooking(MR_BOB_TUESDAY_6_TO_7PM);
        assertEquals(testVenue, otherVenue);
    }

    // Clear expired bookings on venue with expired bookings removes expired bookings
    @Test
    public void clearExpiredBookings_onVenueWithExpiredBooking_removesExpiredBooking() {
        ExpiredBookingStub expiredBookingStub = new ExpiredBookingStub(
                MEETING_ROOM_VENUE_NAME,
                ALICE,
                HP_5_TO_6PM,
                MONDAY);
        Venue someVenue = new VenueBuilder(MEETING_ROOM)
                .withBookings(expiredBookingStub)
                .build();
        someVenue.clearExpiredBookings();
        assertEquals(someVenue, emptyVenue);
    }

    // Clear expired bookings on venue with valid bookings returns the same venue
    @Test
    public void clearExpiredBookings_onVenueWithNoExpiredBookings_returnsSameVenue() {
        Venue testVenueCopy = new VenueBuilder(testVenue).build();
        testVenueCopy.clearExpiredBookings();
        assertEquals(testVenueCopy, testVenue);
    }

    // Our abstract Booking class has only one concrete subclass, i.e. RecurrentBooking, which never expires.
    // However, Booking::hasExpired will be useful when ad-hoc bookings are added for the venues

    // Stub for an expired booking to test Venue::clearExpiredBookings
    private static class ExpiredBookingStub extends Booking {
        ExpiredBookingStub(VenueName venueName, Resident resident, HourPeriod hourPeriod, Day dayOfWeek) {
            super(venueName, resident, hourPeriod, dayOfWeek);
        }
        @Override
        public boolean hasExpired() {
            return true;
        }
    }
}
