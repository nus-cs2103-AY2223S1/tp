package seedu.trackascholar.model.applicant;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Applicant's pin status in TrackAScholar.
 */
public class Pin {

    private final boolean hasPinned;

    /**
     * Constructs an {@code Pin}.
     *
     * @param hasPinned A valid boolean value.
     */
    public Pin(boolean hasPinned) {
        requireNonNull(hasPinned);
        this.hasPinned = hasPinned;
    }

    /**
     * Returns true if the applicant is pinned, false otherwise.
     */
    public boolean getHasPinned() {
        return this.hasPinned;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Pin // instanceof handles nulls
                && hasPinned == (((Pin) other).hasPinned)); // state check
    }

}
