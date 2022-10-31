package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's lesson plan in the address book.
 * Guarantees: immutable; is valid
 */
public class LessonPlan {

    public static final String MESSAGE_CONSTRAINTS = "Lesson plan should not be blank";
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public final String value;

    /**
     * Constructs an {@code LessonPlan}.
     *
     * @param lessonPlan A description of the lesson plan.
     */
    public LessonPlan(String lessonPlan) {
        requireNonNull(lessonPlan);
        checkArgument(isValidLessonPlan(lessonPlan), MESSAGE_CONSTRAINTS);
        value = lessonPlan;
    }

    /**
     * Returns true if a given string is a valid lesson plan.
     */
    public static boolean isValidLessonPlan(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        if (value.equals("")) {
            return "No lesson plan found!";
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonPlan // instanceof handles nulls
                && value.equalsIgnoreCase(((LessonPlan) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
