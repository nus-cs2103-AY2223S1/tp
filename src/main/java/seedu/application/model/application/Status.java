package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import seedu.application.model.CommonRegex;

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
     * Returns true if a given string contains another prefix or prefix, argument pair.
     */
    public static boolean hasUnknownPrefix(String test) {
        String[] filteredTest = test.split(" ");
        if (filteredTest.length >= 2) {
            return filteredTest[1].matches(CommonRegex.VALIDATION_REGEX_FOR_UNKNOWN_PREFIX_INPUT_WITHOUT_SPACE);
        }
        return false; //error not cause by extra prefix
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
