package seedu.rc4hdb.model.venues.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_BOB_TUESDAY_6_TO_7PM;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RecurrentBooking}.
 */
public class RecurrentBookingTest {

    @Test
    public void clashesWith_nullRecurrentBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MR_ALICE_MONDAY_5_TO_6PM.clashesWith(null));
    }

    @Test
    public void clashesWith_overlappingRecurrentBooking_returnTrue() {
        assertTrue(MR_ALICE_MONDAY_5_TO_6PM.clashesWith(MR_ALICE_MONDAY_5_TO_7PM));
    }

    @Test
    public void clashesWith_mutuallyExclusiveRecurrentBooking_returnFalse() {
        assertFalse(MR_ALICE_TUESDAY_6_TO_7PM.clashesWith(MR_ALICE_MONDAY_6_TO_7PM));
    }

    @Test
    public void clashesWith_subsetRecurrentBooking_returnTrue() {
        assertTrue(MR_ALICE_MONDAY_5_TO_6PM.clashesWith(MR_ALICE_MONDAY_5_TO_7PM));
    }

    @Test
    public void clashesWith_differentResidentSameTime_returnTrue() {
        assertTrue(MR_BOB_TUESDAY_6_TO_7PM.clashesWith(MR_ALICE_TUESDAY_6_TO_7PM));
    }

    @Test
    public void hasExpired_recurrentBookingNeverExpires_returnsFalse() {
        assertFalse(MR_ALICE_MONDAY_5_TO_6PM.hasExpired());
    }

}
