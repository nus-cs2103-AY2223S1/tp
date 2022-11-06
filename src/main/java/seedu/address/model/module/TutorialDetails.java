package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's tutorial details in the address book.
 * Guarantees: immutable; is valid as declared in {@link #areValidTutorialDetails(String)}
 */
public class TutorialDetails {

    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial Details are optional but cannot take empty values";
    public static final String EMPTY_TUTORIAL_DETAILS = "";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs {@code TutorialDetails}.
     *
     * @param tutorialDetails Valid tutorial details.
     */
    public TutorialDetails(String tutorialDetails) {
        requireNonNull(tutorialDetails);
        checkArgument(areValidTutorialDetails(tutorialDetails));
        value = tutorialDetails;
    }

    /**
     * Returns true if a given string is a valid tutorial detail.
     */
    public static boolean areValidTutorialDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value == null) {
            return EMPTY_TUTORIAL_DETAILS;
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialDetails // instanceof handles nulls
                && value.equals(((TutorialDetails) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
