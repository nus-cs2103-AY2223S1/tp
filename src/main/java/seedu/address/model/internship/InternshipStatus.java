package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class InternshipStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be PENDING, REJECTED, ACCEPTED or COMPLETED, and it should not be blank";

    public enum State { PENDING, REJECTED, ACCEPTED, COMPLETED }

    public final State currentState;

    public InternshipStatus(State state) {
        this.currentState = state;
    }

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
