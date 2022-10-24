package seedu.address.model.event;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code startDateTime} matches any of the dates given.
 */
public class StartDateTimeContainsDatePredicate implements Predicate<Event> {
    private final List<DateTime> dateTimes;

    public StartDateTimeContainsDatePredicate(List<DateTime> dateTimes) {
        this.dateTimes = dateTimes;
    }

    @Override
    public boolean test(Event event) {
        LocalDate eventStartDate = event.getStartDateTime().getDate();
        return dateTimes.stream()
                .anyMatch(dateTime -> eventStartDate.isEqual(dateTime.date));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTimeContainsDatePredicate // instanceof handles nulls
                && dateTimes.equals(((StartDateTimeContainsDatePredicate) other).dateTimes)); // state check
    }
}
