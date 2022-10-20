package friday.model.grades;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Grade {

    public static final String MESSAGE_CONSTRAINTS = "Grades should be in percentages, excluding the % sign.";
    public static final String VALIDATION_REGEX = "^((100)|(\\d{1,2}(\\.\\d*)?))$";

    public final String examName;
    public final String score;

    /**
     * Constructs a {@code Grade}.
     *
     * @param examName An assessment name.
     * @param score A valid score.
     */
    public Grade(String examName, String score) {
        requireNonNull(score);
        requireNonNull(examName);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.examName = examName;
        this.score = score;
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidScore(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && examName.equals(((Grade) other).examName)
                && score.equals(((Grade) other).score)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(examName, score);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + examName + ": " + score + ']';
    }

}
