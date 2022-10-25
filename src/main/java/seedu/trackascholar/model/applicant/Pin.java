package seedu.trackascholar.model.applicant;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Applicant's pin status in TrackAScholar.
 */
public class Pin {
    private boolean hasPinned;

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
     * Returns value for hasPinned
     *
     */
    public boolean getHasPinned() {
        return this.hasPinned;
    }

    /**
     * Sets hasPinned to the stated boolean value
     *
     * @param hasPinned A valid boolean value.
     */
    public void setHasPinned(boolean hasPinned) {
        this.hasPinned = hasPinned;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Pin // instanceof handles nulls
                && hasPinned == (((Pin) other).hasPinned)); // state check
    }

}
