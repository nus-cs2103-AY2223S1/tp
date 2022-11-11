package longtimenosee.model.event.predicate;

import java.time.LocalDate;
import java.util.function.Predicate;

import longtimenosee.model.event.Event;

/**
 * Tests that a {@code Event} occurs within the next 7 days.
 */
public class WithinNextSevenDays implements Predicate<Event> {

    public WithinNextSevenDays() {
    }
    @Override
    public boolean test(Event event) {
        LocalDate currentDate = LocalDate.now();
        LocalDate weekLater = currentDate.plusDays(7);
        LocalDate eventDate = event.getDate().getDate();
        return (eventDate.isAfter(currentDate) || eventDate.isEqual(currentDate))
                && (eventDate.isBefore(weekLater) || eventDate.isEqual(weekLater));
    }
}
