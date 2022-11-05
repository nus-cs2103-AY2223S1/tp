package longtimenosee.model.event.predicate;

import java.time.LocalDate;
import java.util.function.Predicate;

import longtimenosee.model.event.Event;

/**
 * Tests that an {@code Event}'s {@code Date} matches the date given.
 */
public class EventDateMatchesInputPredicate implements Predicate<Event> {
    private final String date;

    /**
     * Constructs an EventDateMatchesInputPredicate object, which consists of a date input.
     *
     * @param date is the input by the user to be compared.
     */
    public EventDateMatchesInputPredicate(String date) {
        assert date.length() != 0;
        assert date.length() == 10;
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        LocalDate eventDate = event.getDate().getDate();
        LocalDate inputDate = LocalDate.parse(date);
        return eventDate.equals(inputDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof EventDateMatchesInputPredicate) {
                return date.equals(((EventDateMatchesInputPredicate) other).date);
            } else {
                return false;
            }
        }
    }
}

