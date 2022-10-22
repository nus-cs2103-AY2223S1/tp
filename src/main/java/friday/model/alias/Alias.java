package friday.model.alias;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Alias in FRIDAY.
 * Guarantees: immutable; is always valid
 */
public class Alias {

    private final String value;

    /**
     * Constructs an {@code Alias}.
     *
     * @param alias A string alias
     */
    public Alias(String alias) {
        requireNonNull(alias);
        value = alias;
    }

    public static boolean isValidAlias(Alias test) {
        return !ReservedKeyword.LIST_RESERVED_KEYWORDS.contains(test.toString());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof friday.model.alias.Alias // instanceof handles nulls
                && value.equals(((friday.model.alias.Alias) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
