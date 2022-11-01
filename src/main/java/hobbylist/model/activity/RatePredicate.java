package hobbylist.model.activity;

import java.util.function.Predicate;

/**
 * Tests that rate of activity is higher than request.
 */
public class RatePredicate implements Predicate<Activity> {
    private int bound;

    public RatePredicate(int bound) {
        this.bound = bound;
    }

    @Override
    public boolean test(Activity activity) {
        return activity.getRating() >= bound;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RatePredicate // instanceof handles nulls
                && bound == ((RatePredicate) other).bound); // state check
    }
}
