package seedu.application.model.application.interview;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import seedu.application.model.CommonRegex;

/**
 * Represents an Interview's Round. e.g. Round 1 online assessment, Round 2 technical interview, etc.
 * Guarantees: immutable; is valid as declared in {@link #isValidRound(String)}
 */
public class Round {

    public static final String MESSAGE_CONSTRAINTS =
            "Round should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the round must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Round}.
     *
     * @param round A valid Round name.
     */
    public Round(String round) {
        requireNonNull(round);
        checkArgument(isValidRound(round), MESSAGE_CONSTRAINTS);
        value = round;
    }

    /**
     * Returns true if a given string is a valid Round name.
     */
    public static boolean isValidRound(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string contains another prefix or prefix, argument pair.
     */
    public static boolean hasUnknownPrefix(String test) {
        return test.matches(VALIDATION_REGEX + CommonRegex.VALIDATION_REGEX_FOR_EXTRA_PREFIX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Round // instanceof handles nulls
                && value.equals(((Round) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
