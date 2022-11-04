package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalBookings.MONDAY;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_BOB_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.TUESDAY;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.fields.Day;

public class DailyScheduleTest {

    private final List<Booking> bookings = List.of(
            MR_ALICE_MONDAY_5_TO_6PM,
            MR_ALICE_MONDAY_6_TO_7PM,
            MR_BOB_TUESDAY_6_TO_7PM);

    @Test
    public void generateWeeklySchedule_onValidBookings_populatesScheduleWithCorrectResidents() {
        List<DailySchedule> dailyScheduleList = DailySchedule.generateWeeklySchedule(bookings);
        List<Resident> expectedResidents = List.of(ALICE, BOB);
        List<Resident> actualResidents = new ArrayList<>();
        for (DailySchedule dailySchedule : dailyScheduleList) {
            List<Resident> temp = Arrays.stream(dailySchedule.getBookedBy())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            actualResidents.addAll(temp);
        }
        boolean hasSameResidents = expectedResidents.containsAll(actualResidents)
                && actualResidents.containsAll(expectedResidents);
        assertTrue(hasSameResidents);
    }

    @Test
    public void generateWeeklySchedule_onValidBookings_populateScheduleWithCorrectDays() {
        List<DailySchedule> dailyScheduleList = DailySchedule.generateWeeklySchedule(bookings);
        List<Day> expectedDays = List.of(MONDAY, TUESDAY);
        List<Day> actualDays = new ArrayList<>();
        for (DailySchedule dailySchedule : dailyScheduleList) {
            List<Resident> temp = Arrays.stream(dailySchedule.getBookedBy())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if (!temp.isEmpty()) {
                actualDays.add(dailySchedule.getDay());
            }
        }
        boolean hasSameDays = expectedDays.containsAll(actualDays)
                && actualDays.containsAll(expectedDays);
        assertTrue(hasSameDays);
    }

    @Test
    public void generateWeeklySchedule_onInvalidBookings_throwsBookingClashesException() {
        List<Booking> temp = new ArrayList<>(bookings);
        temp.add(MR_ALICE_MONDAY_5_TO_7PM);
        assertThrows(BookingClashesException.class, () ->
                DailySchedule.generateWeeklySchedule(temp));
    }

    @Test
    public void generateWeeklySchedule_onNullBookingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                DailySchedule.generateWeeklySchedule(null));
    }

    @Test
    public void generateWeeklySchedule_onListWithNullBookings_throwsNullPointerException() {
        List<Booking> temp = new ArrayList<>(bookings);
        temp.add(null);
        assertThrows(NullPointerException.class, () ->
                DailySchedule.generateWeeklySchedule(temp));
    }
}
