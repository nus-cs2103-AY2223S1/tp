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

    public final String survey;

    /**
     * Constructs a {@code Survey}.
     *
     * @param survey A valid survey.
     */
    public Survey(String survey) {
        requireNonNull(survey);
        checkArgument(isValidSurvey(survey), MESSAGE_CONSTRAINTS);
        this.survey = survey;
    }

    /**
     * Returns true if a given string is a valid survey.
     */
    public static boolean isValidSurvey(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return survey;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Survey // instanceof handles nulls
                && survey.equals(((Survey) other).survey)); // state check
    }

    @Override
    public int hashCode() {
        return survey.hashCode();
    }
}
