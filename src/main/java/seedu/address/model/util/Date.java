package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateKeyword;

/**
 * Represents Dates that are used in MyInsuRec
 */
public class Date implements Comparable<Date> {
    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    private boolean isAfterDate(LocalDate other) {
        return date.compareTo(other) >= 0;
    }

    private boolean isBeforeDate(LocalDate other) {
        return date.compareTo(other) <= 0;
    }

    /**
     * Returns true if and only if the date checked {@code checkDate} is before today.
     */
    public static boolean isBeforeToday(Date checkDate) {
        LocalDate today = LocalDate.now();
        return checkDate.date.isBefore(today);
    }

    /**
     * Returns true is and only if the date falls within the period {@code keyword}.
     */
    public boolean isInPeriod(DateKeyword keyword) {
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (keyword) {
        case ALL_TIME:
            return true;
        case TOMORROW:
            startDate = today.plusDays(1);
            endDate = today.plusDays(1);
            break;
        case THIS_MONTH:
            startDate = today.withDayOfMonth(1);
            endDate = today.withDayOfMonth(today.getMonth().length(today.isLeapYear()));
            break;
        case THIS_WEEK:
            startDate = today;
            endDate = today.plusDays(7);
            break;
        default:
            startDate = null;
            endDate = null;
        }
        requireNonNull(startDate);
        requireNonNull(endDate);

        return isAfterDate(startDate) && isBeforeDate(endDate);
    }

    @Override
    public int compareTo(Date other) {
        return date.compareTo(other.date);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && date.equals(((Date) other).date));
    }

}
