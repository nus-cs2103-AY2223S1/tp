package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

/**
 * Represents a Status of an internship application.
 * Must be one of the values: interview, offered, pending, rejected.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public enum Status {
    INTERVIEW("interview"),
    OFFERED("offered"),
    PENDING("pending"),
    REJECTED("rejected");

    public static final String MESSAGE_CONSTRAINTS =
            "Status should be one of the following: pending, interview, offered, rejected.";

    private final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param value A valid value in String.
     */
    private Status(String value) {
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (Status s : values()) {
            if (s.value.equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Status if a given string is a valid status.
     */
    public static Status getStatus(String value) {
        requireNonNull(value);
        checkArgument(isValidStatus(value), MESSAGE_CONSTRAINTS);
        for (Status s : values()) {
            if (s.value.equals(value)) {
                return s;
            }
        }
        return null; // IllegalArgumentException should have been thrown.
    }

    /**
     * Returns the String value of the status.
     */
    public String getValue() {
        return value;
    }
}
