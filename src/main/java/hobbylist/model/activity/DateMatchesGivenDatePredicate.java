package hobbylist.model.activity;

import java.util.List;
import java.util.function.Predicate;

import hobbylist.commons.util.StringUtil;

/**
 * Tests that an {@code Activity}'s {@code Date} matches any of the given date.
 */
public class DateMatchesGivenDatePredicate implements Predicate<Activity> {
    private final String time;

    public DateMatchesGivenDatePredicate(String time) {
        this.time = time;
    }

    @Override
    public boolean test(Activity activity) {
        if (!activity.getDate().isEmpty()) {
            if (StringUtil.containsWordIgnoreCase(activity.getDate().toString(), time)) {
                return true;
            }
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
