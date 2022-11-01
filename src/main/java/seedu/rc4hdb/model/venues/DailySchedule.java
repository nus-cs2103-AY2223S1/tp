package seedu.rc4hdb.model.venues;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.ui.BookingTableView;

/**
 * Encapsulates the data required to populate a row in the {@link BookingTableView}.
 */
public class DailySchedule {

    private final Day day;
    private final Resident[] bookedBy;

    private DailySchedule(Resident[] bookedBy, Day day) {
        requireAllNonNull(bookedBy, day);
        this.day = day;
        this.bookedBy = bookedBy;
    }

    //====================== Getters required to populate Booking Table ===============================

    public Day getDay() {
        return day;
    }

    public Resident[] getBookedBy() {
        return bookedBy;
    }

    //====================== End of getters ===========================================================

    /**
     * Populates the {@code bookedBy} array with their respective residents.
     */
    private static void populateBookedByList(Resident[] bookedBy, HourPeriod hourPeriod, Resident resident)
            throws BookingClashesException {
        int start = hourPeriod.getStart().asInt();
        int end = hourPeriod.getEnd().asInt();

        for (int curr = start; curr < end; curr++) {
            if (bookedBy[curr] != null) {
                throw new BookingClashesException();
            }
            bookedBy[curr] = resident;
        }
    }

    /**
     * Build the {@code bookedBy} array with the {@code bookings}.
     */
    private static Resident[] buildBookedByList(List<Booking> bookings) throws BookingClashesException {
        requireNonNull(bookings);
        Resident[] bookedBy = new Resident[24];
        for (Booking booking : bookings) {
            populateBookedByList(bookedBy, booking.getHourPeriod(), booking.getResident());
        }
        return bookedBy;
    }

    /**
     * Factory method for producing a list of {@code DailySchedule} instances which represent a weekly schedule.
     */
    public static List<DailySchedule> generateWeeklySchedule(List<Booking> bookings) throws BookingClashesException {
        requireNonNull(bookings);
        return Day.DAYS_OF_WEEK.stream().map(Day::new).map(day ->
                new DailySchedule(buildBookedByList(bookings.stream()
                        .filter(booking -> booking.isSameDay(day))
                        .collect(Collectors.toList())), day)).collect(Collectors.toList());
    }

}
