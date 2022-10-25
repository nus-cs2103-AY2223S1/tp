package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a key for grouping appointments.
 * Guarantees: immutable; name is valid as declared in {@link #isValidKey(String)}
 */
public enum Key {
    PATIENT("Patient"), TAG("Tag"), MARK("Mark");

    public static final String MESSAGE_CONSTRAINTS = "Key should be either tag or patient.";
    private String keyContent;

    Key(String keyContent) {
        requireNonNull(keyContent);
        checkArgument(isValidKey(keyContent), MESSAGE_CONSTRAINTS);
        this.keyContent = keyContent;
    }

    @Override
    public String toString() {
        return keyContent;
    }

    /**
     * Checks whether the given string is a valid key.
     *
     * @param keyContent The string to test.
     * @return The result of the equals test.
     */
    public static boolean isValidKey(String keyContent) {
        return keyContent.equalsIgnoreCase("patient")
                || keyContent.equalsIgnoreCase("tag")
                || keyContent.equalsIgnoreCase("mark")
                || keyContent.equalsIgnoreCase("p")
                || keyContent.equalsIgnoreCase("t")
                || keyContent.equalsIgnoreCase("m");
    }

    /**
     * Converts the given input to {@code Key} if possible.
     *
     * @param keyContent The given input.
     * @return The resulting {@code Key}.
     */
    public static Key convertToKey(String keyContent) {
        requireNonNull(keyContent);
        if (keyContent.equalsIgnoreCase("patient")
                || keyContent.equalsIgnoreCase("p")) {
            return Key.PATIENT;
        } else if (keyContent.equalsIgnoreCase("tag")
                || keyContent.equalsIgnoreCase("t")) {
            return Key.TAG;
        } else if (keyContent.equalsIgnoreCase("mark")
                || keyContent.equalsIgnoreCase("m")) {
            return Key.MARK;
        } else {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }
}
