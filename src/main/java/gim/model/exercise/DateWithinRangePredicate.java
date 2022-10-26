package gim.model.exercise;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.function.Predicate;

import gim.model.date.Date;

/**
 * Tests that a {@code Exercise}'s {@code Date} is between the
 * startDate (inclusive) and endDate (inclusive).
 */
public class DateWithinRangePredicate implements Predicate<Exercise> {
    private final Date startDate;
    private final Date endDate;

    /**
     * Constructor for DateWithinRangePredicate
     * @param start start date (inclusive)
     * @param end end date (inclusive)
     */
    public DateWithinRangePredicate(Date start, Date end) {
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Computes the number of logical calendar days between startDate and endDate.
     * @return number of days
     */
    public int getRangeSizeInDays() {
        return (int) DAYS.between(startDate.date, endDate.date);
    }

    @Override
    public boolean test(Exercise exercise) {
        return exercise.getDate().checkWithinRange(startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateWithinRangePredicate // instanceof handles nulls
                && ((DateWithinRangePredicate) other).startDate.equals(startDate)
                && ((DateWithinRangePredicate) other).endDate.equals(endDate));
    }

}
