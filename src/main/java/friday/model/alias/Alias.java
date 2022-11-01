package friday.model.alias;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Alias in FRIDAY.
 */
public class Alias {

    private final String value;
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";

    /**
     * Constructs an {@code Alias}.
     *
     * @param alias A string alias
     */
    public Alias(String alias) {
        requireNonNull(alias);
        value = alias;
    }

    /**
     * Returns if a given String is a valid alias.
     */
    public static boolean isValidAlias(String test) {
        return !ReservedKeyword.LIST_RESERVED_KEYWORDS.contains(test)
                && !test.contains(SPACE)
                && !test.equals(EMPTY_STRING);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Alias // instanceof handles nulls
                && value.equals(((Alias) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
