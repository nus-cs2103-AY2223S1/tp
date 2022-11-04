package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.util.AppUtil.checkArgument;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    public static final String MESSAGE_CONSTRAINTS =
            "Prefix names should be alphanumeric and should contain at least 1 character";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+\\/";

    private final String prefix;

    /**
     * Constructs a {@code Prefix}.
     *
     * @param prefix A valid prefix name.
     */
    public Prefix(String prefix) throws IllegalArgumentException {
        checkArgument(isValidPrefixName(prefix) || prefix.trim().equals(""), MESSAGE_CONSTRAINTS);
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }

    /**
     * Returns true if a given string is a valid prefix name.
     */
    public static boolean isValidPrefixName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
