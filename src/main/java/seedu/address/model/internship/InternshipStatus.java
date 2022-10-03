package seedu.address.model.internship;

/**
 * Represents an Internship's status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class InternshipStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be PENDING, REJECTED, ACCEPTED or COMPLETED, and it should not be blank";

    /**
     * Represents the possible states of an InternshipStatus.
     */
    public enum State { PENDING, REJECTED, ACCEPTED, COMPLETED }

    public final State currentState;

    /**
     * Constructs a {@code InternshipStatus}.
     *
     * @param state A valid state as defined in the {@Code State} enum.
     */
    public InternshipStatus(State state) {
        this.currentState = state;
    }

    /**
     * Returns true if the given String can be parsed to a valid state as defined in the {@Code State} enum.
     */
    public static boolean isValidStatus(String test) {
        switch (test) {
        case "PENDING":
        case "REJECTED":
        case "ACCEPTED":
        case "COMPLETED":
            return true;
        default:
            return false;
        }
    }

    @Override
    public String toString() {
        return currentState.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipStatus // instanceof handles nulls
                && currentState.equals(((InternshipStatus) other).currentState)); // state check
    }

    @Override
    public int hashCode() {
        return currentState.hashCode();
    }
}
