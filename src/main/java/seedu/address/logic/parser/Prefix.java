package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {

    private static final Pattern PREFIX_VALIDATION_REGEX = Pattern.compile("\\p{Alnum}*/");

    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    /**
     * Checks whether a string is a valid prefix.
     *
     * @param prefix a String representing a prefix.
     * @return true if the string can be a prefix, false otherwise.
     */
    public static boolean isValidPrefix(String prefix) {
        requireNonNull(prefix);
        return PREFIX_VALIDATION_REGEX.matcher(prefix).matches();
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
}
