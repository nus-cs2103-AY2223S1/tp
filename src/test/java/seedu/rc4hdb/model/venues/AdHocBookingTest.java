package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;

import org.junit.jupiter.api.Test;
import seedu.rc4hdb.model.venues.booking.AdHocBooking;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;

public class AdHocBookingTest {
    private static final AdHocBooking SEPT_20_2022_8_TO_9_PM = new AdHocBooking(ALICE, "2022-09-20", "20:00", "21:00");
    private static final AdHocBooking SEPT_20_2022_5_TO_10_PM = new AdHocBooking(ALICE, "2022-09-20", "17:00", "22:00");
    private static final AdHocBooking SEPT_20_2099_7_TO_11_PM = new AdHocBooking(ALICE, "2099-09-20", "19:00", "23:00");
    private static final RecurrentBooking TUESDAY_730_TO_9PM = new RecurrentBooking(ALICE, "TUESDAY", "19:30", "21:00");

    @Test
    public void adHocBooking1_hasExpired_afterEndTimeExceeded() {
        assertTrue(SEPT_20_2022_8_TO_9_PM.hasExpired());
    }

    @Test
    public void adHocBooking2_hasExpired_afterEndTimeExceeded() {
        assertTrue(SEPT_20_2022_5_TO_10_PM.hasExpired());
    }

    @Test
    public void adHocBooking3_withDistantEndDate_hasNotExpired() {
        assertFalse(SEPT_20_2099_7_TO_11_PM.hasExpired());
    }

    @Test
    public void adHocBooking_clashesWith_overlappingAdHocBooking() {
        assertTrue(SEPT_20_2022_5_TO_10_PM.clashesWith(SEPT_20_2022_8_TO_9_PM));
    }

    @Test
    public void adHocBooking_doesNotClashWith_mutuallyExclusiveAdHocBooking() {
        assertFalse(SEPT_20_2022_5_TO_10_PM.clashesWith(SEPT_20_2099_7_TO_11_PM));
    }

    @Test
    public void adHocBooking_clashesWith_overlappingRecurrentBooking() {
        assertTrue(TUESDAY_730_TO_9PM.clashesWith(SEPT_20_2022_5_TO_10_PM));
    }
}
