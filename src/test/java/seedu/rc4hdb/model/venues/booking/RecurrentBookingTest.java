package seedu.rc4hdb.model.venues.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Unit tests for {@link RecurrentBooking}.
 */
public class RecurrentBookingTest {
    private static final Hour HOUR_5PM = new Hour("17");
    private static final Hour HOUR_6PM = new Hour("18");
    private static final Hour HOUR_7PM = new Hour("19");

    private static final Day MONDAY = new Day("MON");
    private static final Day TUESDAY = new Day("TUE");

    private static final RecurrentBooking ALICE_MONDAY_5_TO_6_PM =
            new RecurrentBooking(ALICE, HOUR_5PM, HOUR_6PM, MONDAY, Venue.MEETING_ROOM);
    private static final RecurrentBooking ALICE_MONDAY_6_TO_7PM =
            new RecurrentBooking(ALICE, HOUR_6PM, HOUR_7PM, MONDAY, Venue.MEETING_ROOM);
    private static final RecurrentBooking ALICE_MONDAY_5_TO_7PM =
            new RecurrentBooking(ALICE, HOUR_5PM, HOUR_7PM, MONDAY, Venue.MEETING_ROOM);
    private static final RecurrentBooking ALICE_TUESDAY_6_TO_7PM =
            new RecurrentBooking(ALICE, HOUR_6PM, HOUR_7PM, TUESDAY, Venue.MEETING_ROOM);
    private static final RecurrentBooking BOB_TUESDAY_6_TO_7PM =
            new RecurrentBooking(BOB, HOUR_6PM, HOUR_7PM, TUESDAY, Venue.MEETING_ROOM);

    @Test
    public void clashesWith_overlappingRecurrentBooking_returnTrue() {
        assertTrue(ALICE_MONDAY_5_TO_6_PM.clashesWith(ALICE_MONDAY_5_TO_7PM));
    }

    @Test
    public void clashesWith_mutuallyExclusiveRecurrentBooking_returnFalse() {
        assertFalse(ALICE_TUESDAY_6_TO_7PM.clashesWith(ALICE_MONDAY_6_TO_7PM));
    }

    @Test
    public void clashesWith_subsetRecurrentBooking_returnTrue() {
        assertTrue(ALICE_MONDAY_5_TO_6_PM.clashesWith(ALICE_MONDAY_5_TO_7PM));
    }

    @Test
    public void clashesWith_differentResidentSameTime_returnTrue() {
        assertTrue(BOB_TUESDAY_6_TO_7PM.clashesWith(ALICE_TUESDAY_6_TO_7PM));
    }

    @Test
    public void hasExpired_recurrentBookingNeverExpires_returnsFalse() {
        assertFalse(ALICE_MONDAY_5_TO_6_PM.hasExpired());
    }
}
