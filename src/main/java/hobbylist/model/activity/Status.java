package hobbylist.model.activity;

import static hobbylist.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

// Solution adapted from https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files

/**
 * Represents a Status of an activity in HobbyList.
 */
public class Status {
    public static final String STATUS_UPCOMING = "UPCOMING";
    public static final String STATUS_ONGOING = "ONGOING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String MESSAGE_CONSTRAINT = "Completion status should be either UPCOMING"
            + ", ONGOING or COMPLETED.";

    /**
     * Different status of an activity.
     */
    public enum State {
        NONE, UPCOMING, ONGOING, COMPLETED
    }

    public final State status;

    public Status() {
        status = State.NONE;
    }

    /**
     * Constructs a {@code Status}.
     * @param status
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINT);
        this.status = getState(status);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String status) {
        String toCheck = status.toUpperCase();
        return (toCheck.equals("NONE") || toCheck.equals("UPCOMING")
                || toCheck.equals("ONGOING") || toCheck.equals("COMPLETED"));
    }

    private static State getState(String status) {
        String state = status.toUpperCase();
        if (state.equals("UPCOMING")) {
            return State.UPCOMING;
        }
        if (state.equals("ONGOING")) {
            return State.ONGOING;
        }
        if (state.equals("COMPLETED")) {
            return State.COMPLETED;
        }
        return State.NONE;
    }

    public boolean hasStatus() {
        return status != State.NONE;
    }

    /**
     * Return true if the current status matches the given String
     * @param status
     * @return
     */
    public boolean match(String status) {
        String state = status.toUpperCase();
        if (state.equals("UPCOMING")) {
            return this.status.equals(State.UPCOMING);
        } else if (state.equals("ONGOING")) {
            return this.status.equals(State.ONGOING);
        } else if (state.equals("COMPLETED")) {
            return this.status.equals(State.COMPLETED);
        } else {
            return this.status.equals(State.NONE);
        }
    }

    @Override
    public String toString() {
        return status.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Status
                && status.equals(((Status) other).status));
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
