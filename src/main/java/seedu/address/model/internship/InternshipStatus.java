package seedu.address.model.internship;

/**
 * Represents an Internship's status in InterNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class InternshipStatus {

    public static final String MESSAGE_CONSTRAINTS = "Status can only be "
            + "BOOKMARKED, PENDING, ACCEPTED, COMPLETED or REJECTED "
            + "(short-forms corresponding to first letter: B, P, A, C, R), and are case-insensitive. "
            + "Status should not be blank.";

    /**
     * Represents the possible states of an InternshipStatus.
     * A lower level indicates a higher priority in the Internship list when sorted by InternshipStatus.
     */
    public enum State {
        BOOKMARKED(0),
        PENDING(1),
        ACCEPTED(2),
        COMPLETED(3),
        REJECTED(4);

        private final int level;

        State(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        /**
         * Constructs a State from trimmed user input.
         * Handles shortcut inputs, given by the first character of the State.
         *
         * @param input that is trimmed and is uppercase.
         * @return State enum of specified internship status.
         */
        public static State fromTrimmedInput(String input) {
            if (input.length() == 1) {
                return State.fromShortcut(input);
            }
            return State.valueOf(input);
        }

        private static State fromShortcut(String shortcut) {
            assert shortcut.length() == 1;
            switch (shortcut) {
            case "B":
                return State.BOOKMARKED;
            case "P":
                return State.PENDING;
            case "A":
                return State.ACCEPTED;
            case "C":
                return State.COMPLETED;
            case "R":
                return State.REJECTED;
            default:
                return null;
            }
        }

    }

    public final State currentState;

    /**
     * Constructs a {@code InternshipStatus}.
     *
     * @param state A valid state as defined in the {@code State} enum.
     */
    public InternshipStatus(State state) {
        this.currentState = state;
    }

    /**
     * Returns true if the given String can be parsed to a valid state as defined in the {@code State} enum.
     */
    public static boolean isValidStatus(String test) {
        switch (test) {
        case "B":
            // Fallthrough
        case "P":
            // Fallthrough
        case "A":
            // Fallthrough
        case "C":
            // Fallthrough
        case "R":
            // Fallthrough
        case "BOOKMARKED":
            // Fallthrough
        case "ACCEPTED":
            // Fallthrough
        case "COMPLETED":
            // Fallthrough
        case "PENDING":
            // Fallthrough
        case "REJECTED":
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
