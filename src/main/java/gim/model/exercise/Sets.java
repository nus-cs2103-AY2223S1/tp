package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents an Exercise's sets in Gim.
 * Guarantees: immutable; is valid as declared in {@link #isValidSets(String)}
 */
public class Sets {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Sets should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Sets}.
     *
     * @param sets A valid sets number.
     */
    public Sets(String sets) {
        requireNonNull(sets);
        checkArgument(isValidSets(sets), MESSAGE_CONSTRAINTS);
        value = sets;
    }

    /**
     * Returns if a given string is a valid sets.
     */
    public static boolean isValidSets(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sets // instanceof handles nulls
                && value.equals(((Sets) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
