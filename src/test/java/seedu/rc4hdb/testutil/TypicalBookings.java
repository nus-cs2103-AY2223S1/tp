package seedu.rc4hdb.testutil;

import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    public static final HourPeriod HP_5_TO_6PM = new HourPeriod("17-18");
    public static final HourPeriod HP_6_TO_7PM = new HourPeriod("18-19");
    public static final HourPeriod HP_5_TO_7PM = new HourPeriod("17-19");

    public static final Day MONDAY = new Day("MON");
    public static final Day TUESDAY = new Day("TUE");

    /* Meeting room bookings */
    public static final RecurrentBooking MR_ALICE_MONDAY_5_TO_6_PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_5_TO_6PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_MONDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_6_TO_7PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_MONDAY_5_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_5_TO_7PM, MONDAY);

    public static final RecurrentBooking MR_ALICE_TUESDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, ALICE, HP_6_TO_7PM, TUESDAY);

    public static final RecurrentBooking MR_BOB_TUESDAY_6_TO_7PM =
            new RecurrentBooking(MEETING_ROOM_VENUE_NAME, BOB, HP_6_TO_7PM, TUESDAY);

}
