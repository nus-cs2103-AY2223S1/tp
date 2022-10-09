package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutorial's module in the ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class TutorialModule {

    public static final String MESSAGE_CONSTRAINTS =
            "Module's name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the module's name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String moduleName;

    /**
     * Constructs a {@code TutorialVenue}.
     *
     * @param moduleName A valid venue.
     */
    public TutorialModule(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModule(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    /**
     * Returns true if a given string is a valid venue.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialModule // instanceof handles nulls
                && moduleName.equals(((TutorialModule) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

}
