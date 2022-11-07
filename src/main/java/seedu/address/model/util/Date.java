package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateKeyword;

/**
 * Represents Dates that are used in MyInsuRec
 */
public abstract class Date implements Comparable<Date> {
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
     * Returns true is and only if the date falls within the period {@code keyword}
     * with respect to a givenDate {@code givenDate}.
     */
    public boolean isInPeriod(DateKeyword keyword, LocalDate givenDate) {
        requireNonNull(keyword);
        requireNonNull(givenDate);

        LocalDate startDate;
        LocalDate endDate;

        switch (keyword) {
        case TOMORROW:
            startDate = givenDate.plusDays(1);
            endDate = givenDate.plusDays(1);
            break;
        case THIS_MONTH:
            startDate = givenDate.withDayOfMonth(1);
            endDate = givenDate.withDayOfMonth(givenDate.getMonth().length(givenDate.isLeapYear()));
            break;
        case THIS_WEEK:
            startDate = givenDate;
            endDate = givenDate.plusDays(7);
            break;
        default:
            return true;
        }

        return isAfterDate(startDate) && isBeforeDate(endDate);
    }

    /**
     * Returns true is and only if the date falls within the period {@code period}
     * with respect to the present.
     */
    public boolean isInPeriod(DateKeyword period) {
        LocalDate today = LocalDate.now();
        return isInPeriod(period, today);
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

    /**
     * Returns true if and only if the date checked {@code checkDate} is before today.
     */
    public static boolean isBeforeToday(Date checkDate) {
        LocalDate today = LocalDate.now();
        return checkDate.date.isBefore(today);
    }

    /**
     * Returns true if and only if the date checked {@code checkDate} is within the period {@code}.
     */
    public static boolean isBeforePeriod(Date checkDate, int period) {
        return !checkDate.date.isAfter(LocalDate.now())
                && !checkDate.date.isBefore(LocalDate.now().minusYears(period));
    }
}
