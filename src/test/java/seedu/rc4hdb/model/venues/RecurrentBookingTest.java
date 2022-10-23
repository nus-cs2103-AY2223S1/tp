package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;

import org.junit.jupiter.api.Test;
import seedu.rc4hdb.model.venues.booking.AdHocBooking;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;

public class RecurrentBookingTest {
    private static final RecurrentBooking MONDAY_5_TO_6_PM = new RecurrentBooking(ALICE, "MONDAY", "17:00", "18:00");
    private static final RecurrentBooking MONDAY_530_TO_630PM = new RecurrentBooking(ALICE, "MONDAY", "17:30", "18:30");
    private static final RecurrentBooking TUESDAY_730_TO_9PM = new RecurrentBooking(ALICE, "TUESDAY", "19:30", "21:00");
    private static final AdHocBooking OCT_17_2022_5_TO_6_PM = new AdHocBooking(ALICE, "2022-10-17", "17:00", "18:00");

    @Test
    public void recurrentBooking_clashesWith_overlappingRecurrentBooking() {
        assertTrue(MONDAY_5_TO_6_PM.clashesWith(MONDAY_530_TO_630PM));
    }

    @Test
    public void recurrentBooking_doesNotClashWith_mutuallyExclusiveRecurrentBooking() {
        assertFalse(MONDAY_5_TO_6_PM.clashesWith(TUESDAY_730_TO_9PM));
    }

    @Test
    public void recurrentBooking_clashesWith_overlappingAdHocBooking() {
        assertTrue(MONDAY_530_TO_630PM.clashesWith(OCT_17_2022_5_TO_6_PM));
    }

    @Test
    public void recurrentBooking_clashesWith_subsetRecurrentBooking() {
        assertTrue(MONDAY_530_TO_630PM.clashesWith(MONDAY_5_TO_6_PM));
    }

    @Test
    public void recurrentBooking_hasExpired_returnsFalse() {
        assertFalse(MONDAY_530_TO_630PM.hasExpired());
    }
}
