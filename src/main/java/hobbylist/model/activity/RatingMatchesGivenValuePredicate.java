package hobbylist.model.activity;

import java.util.function.Predicate;

/**
 * Tests that an {@code Activity}'s {@code Rating} matches the given rating value.
 */
public class RatingMatchesGivenValuePredicate implements Predicate<Activity> {
    private final int value;

    public RatingMatchesGivenValuePredicate(int value) {
        this.value = value;
    }

    @Override
    public boolean test(Activity activity) {
        if (value == -1) {
            return false;
        }
        return activity.getRating() != 0 && activity.getRating() == value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RatingMatchesGivenValuePredicate // instanceof handles nulls
                && value == ((RatingMatchesGivenValuePredicate) other).value); // state check
    }
}
