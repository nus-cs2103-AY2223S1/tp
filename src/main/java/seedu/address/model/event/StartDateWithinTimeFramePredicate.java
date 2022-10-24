package seedu.address.model.event;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code startDateTime} is within a given time frame.
 */
public class StartDateWithinTimeFramePredicate implements Predicate<Event> {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for StartDateWithinTimeFramePredicate
     */
    public StartDateWithinTimeFramePredicate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns true when event starts after startDate and before or on endDate.
     */
    @Override
    public boolean test(Event event) {
        LocalDate eventStartDate = event.getStartDateTime().date;
        return startDate.isBefore(eventStartDate)
                && (endDate.isAfter(eventStartDate) || endDate.isEqual(eventStartDate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartDateWithinTimeFramePredicate)) {
            return false;
        }

        // state check
        StartDateWithinTimeFramePredicate otherStartDateWithinTimeFramePredicate =
                (StartDateWithinTimeFramePredicate) other;

        return startDate.equals(otherStartDateWithinTimeFramePredicate.startDate)
                && endDate.equals(otherStartDateWithinTimeFramePredicate.endDate);
    }

}
