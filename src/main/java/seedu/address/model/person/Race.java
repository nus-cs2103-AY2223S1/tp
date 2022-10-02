package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's race in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidRace(String)}
 */
public class Race {

    public static final String MESSAGE_CONSTRAINTS =
            "Race should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String race;

    /**
     * Constructs a {@code Race}.
     *
     * @param race A valid race.
     */
    public Race(String race) {
        requireNonNull(race);
        checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        this.race = race;
    }

    /**
     * Returns true if a given string is a valid race.
     */
    public static boolean isValidRace(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return race;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Race // instanceof handles nulls
                && race.equals(((Race) other).race)); // state check
    }

    @Override
    public int hashCode() {
        return race.hashCode();
    }
}
