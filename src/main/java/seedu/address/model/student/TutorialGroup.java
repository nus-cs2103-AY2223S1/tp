package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's tutorial group in the address book.
 */
public class TutorialGroup {
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial group should follow the format Txx, where x is a numeric value, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[T[0-9][0-9]]";

    public final String tutorialGroup;

    /**
     * Constructs a {@code TutorialGroup}.
     *
     * @param group A valid tutorial group.
     */
    public TutorialGroup(String group) {
        requireNonNull(group);
        checkArgument(isValidTutorialGroup(group), MESSAGE_CONSTRAINTS);
        tutorialGroup = group;
    }

    /**
     * Returns true if a given string is a valid tutorial group.
     */
    public static boolean isValidTutorialGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return tutorialGroup;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && tutorialGroup.equals(((TutorialGroup) other).tutorialGroup)); // state check
    }

    @Override
    public int hashCode() {
        return tutorialGroup.hashCode();
    }

}
