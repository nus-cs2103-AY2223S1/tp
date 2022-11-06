package seedu.rc4hdb.testutil;

import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    /* Typical hour period fields */
    public static final String HP_5_TO_6PM_STRING = "17-18";
    public static final String HP_6_TO_7PM_STRING = "18-19";
    public static final String HP_5_TO_7PM_STRING = "17-19";

    public static final HourPeriod HP_5_TO_6PM = new HourPeriod(HP_5_TO_6PM_STRING);
    public static final HourPeriod HP_6_TO_7PM = new HourPeriod(HP_6_TO_7PM_STRING);
    public static final HourPeriod HP_5_TO_7PM = new HourPeriod(HP_5_TO_7PM_STRING);

    /* Typical day fields */
    public static final String MONDAY_STRING = "MON";
    public static final String TUESDAY_STRING = "TUE";

    public static final String WEDNESDAY_STRING = "WED";

    public static final Day MONDAY = new Day(MONDAY_STRING);
    public static final Day TUESDAY = new Day(TUESDAY_STRING);
    public static final Day WEDNESDAY = new Day(WEDNESDAY_STRING);

    /* Meeting room bookings */
    public static final RecurrentBooking MR_ALICE_MONDAY_5_TO_6PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_5_TO_6PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_MONDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_6_TO_7PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_MONDAY_5_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_5_TO_7PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_TUESDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_6_TO_7PM, TUESDAY);

    public static final RecurrentBooking MR_BOB_TUESDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, BOB, HP_6_TO_7PM, TUESDAY);

    public static final RecurrentBooking MR_BOB_WEDNESDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, BOB, HP_6_TO_7PM, WEDNESDAY);

    /**
     * Returns a booking descriptor with contents equal to MR_ALICE_MONDAY_5_TO_6PM.
     * @return An example booking descriptor for testing purposes
     */
    public static BookingDescriptor getExampleBookingDescriptor() {
        BookingDescriptor bookingDescriptor = new BookingDescriptor();
        bookingDescriptor.setResident(ALICE);
        bookingDescriptor.setDayOfWeek(MONDAY);
        bookingDescriptor.setVenueName(MEETING_ROOM_VENUE_NAME);
        bookingDescriptor.setHourPeriod(HP_5_TO_6PM);
        return bookingDescriptor;
    }

}
