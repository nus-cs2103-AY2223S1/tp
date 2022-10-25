package seedu.rc4hdb.ui;

import java.util.List;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Encapsulates the data required to populate a row in the {@link BookingTableView}.
 */
public class DailySchedule {

    private final Day day;
    private final Resident[] bookedBy;

    private DailySchedule(Resident[] bookedBy, Day day) {
        this.day = day;
        this.bookedBy = bookedBy;
    }

    /**
     * Checks if all bookings are on {@code day}.
     */
    private static boolean checkBookingDay(List<Booking> bookings, Day day) {
        return bookings.stream().map(Booking::getDayOfWeek)
                .allMatch(day::equals);
    }

    public Day getDay() {
        return day;
    }

    public Resident[] getBookedBy() {
        return bookedBy;
    }

    /**
     * Populates the {@code bookedBy} array with their respective residents.
     */
    private static void populateBookedByList(Resident[] bookedBy, HourPeriod hourPeriod, Resident resident) {
        int start = hourPeriod.getStart().asInt();
        int end = hourPeriod.getEnd().asInt();

        for (int curr = start; curr < end; curr++) {
            if (bookedBy[curr] != null) {
                // to throw error for overlapping booking hour
            }
            bookedBy[curr] = resident;
        }
    }

    /**
     * Build the {@code bookedBy} array with the {@code bookings}.
     */
    private static Resident[] buildBookedByList(List<Booking> bookings) {
        Resident[] bookedBy = new Resident[24];
        for (Booking booking : bookings) {
            populateBookedByList(bookedBy, booking.getHourPeriod(), booking.getResident());
        }
        return bookedBy;
    }

    /**
     * Factory method for producing {@code DailySchedule} instances of a certain {@code day} using {@code bookings}.
     */
    public static DailySchedule of(List<Booking> bookings, Day day) {
        if (!checkBookingDay(bookings, day)) {
            // to throw error for bookings not on same day
        }
        return new DailySchedule(buildBookedByList(bookings), day);
    }

}
