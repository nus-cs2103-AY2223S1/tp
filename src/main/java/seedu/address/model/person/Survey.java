package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of the survey a Person in the application has taken.
 * Guarantees: immutable; is valid as declared in {@link #isValidSurvey(String)}
 */
public class Survey {

    public static final String MESSAGE_CONSTRAINTS =
            "Survey should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final String DONE = "DONE";
    private static final String NOT_DONE = "NOT_DONE";

    public final String survey;
    public final boolean isDone;

    /**
     * Constructs a {@code Survey}.
     *
     * @param survey A valid survey.
     */
    public Survey(String survey, boolean isDone) {
        requireNonNull(survey);
        checkArgument(isValidSurvey(survey), MESSAGE_CONSTRAINTS);
        this.survey = survey;
        this.isDone = isDone;
    }

    public Survey(String survey) {
        this(survey, false);
    }

    /**
     * Returns true if a given string is a valid survey.
     */
    public static boolean isValidSurvey(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Survey)) {
            return false;
        }

        Survey otherSurvey = (Survey) other;
        return survey.equals(otherSurvey.survey) && isDone == otherSurvey.isDone;
    }

    @Override
    public int hashCode() {
        return survey.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s, %s]", survey, getStatus());
    }

    private String getStatus() {
        return isDone ? DONE : NOT_DONE;
    }
}
