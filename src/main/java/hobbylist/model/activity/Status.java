package hobbylist.model.activity;

import static hobbylist.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;

// Solution adapted from https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files

/**
 * Represents a Status of an activity in HobbyList.
 */
public class Status {
    public static final String STATUS_UPCOMING = "UPCOMING";
    public static final String STATUS_ONGOING = "ONGOING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_NONE = "NONE";
    public static final String MESSAGE_CONSTRAINT = "Completion status should be either UPCOMING"
            + ", ONGOING or COMPLETED.";
    public static final String[] VALID_STATUSES = {STATUS_COMPLETED, STATUS_ONGOING, STATUS_UPCOMING, STATUS_NONE};

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
        return (Arrays.asList(VALID_STATUSES).contains(toCheck));
    }

    private static State getState(String status) {
        String state = status.toUpperCase();
        switch (state) {
        case STATUS_UPCOMING:
            return State.UPCOMING;
        case STATUS_ONGOING:
            return State.ONGOING;
        case STATUS_COMPLETED:
            return State.COMPLETED;
        default:
            return State.NONE;
        }
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
        switch (state) {
        case STATUS_UPCOMING:
            return this.status.equals(State.UPCOMING);
        case STATUS_ONGOING:
            return this.status.equals(State.ONGOING);
        case STATUS_COMPLETED:
            return this.status.equals(State.COMPLETED);
        default:
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
