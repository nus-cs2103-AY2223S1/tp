package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutorial's module in the ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class TutorialModule {

    public static final String MESSAGE_CONSTRAINTS =
            "Module's code should consists of a two- or three-letter prefix, four digits"
                + " and optionally a one-letter suffix";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,3}[\\d]{4}[a-zA-Z]?";

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
