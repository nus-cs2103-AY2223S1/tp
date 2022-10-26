package hobbylist.model.activity;

import java.util.function.Predicate;

/**
 * Tests that an {@code Activity}'s {@code Status} matches given status.
 */
public class StatusMatchesGivenStatus implements Predicate<Activity> {
    private final String status;

    public StatusMatchesGivenStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean test(Activity activity) {
        Status status = activity.getStatus();
        return status.match(this.status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof hobbylist.model.activity.StatusMatchesGivenStatus // instanceof handles nulls
                && status.equals(((hobbylist.model.activity.StatusMatchesGivenStatus) other).status)); // state check
    }
}
