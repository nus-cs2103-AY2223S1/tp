package hobbylist.model.activity;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests that an {@code Activity}'s {@code Date} matches the given date.
 */
public class DateMatchesGivenDatePredicate implements Predicate<Activity> {
    private final String time;

    public DateMatchesGivenDatePredicate(String time) {
        this.time = time;
    }

    @Override
    public boolean test(Activity activity) {
        if (Objects.equals(time, "")) {
            return false;
        }
        if (activity.getDate().isPresent()) {
            return activity.getDate().get().getOriginalString().contains(time);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateMatchesGivenDatePredicate // instanceof handles nulls
                && time.equals(((DateMatchesGivenDatePredicate) other).time)); // state check
    }
}
