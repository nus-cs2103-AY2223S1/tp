package seedu.application.model.application.interview;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Date of Interview for the specific Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class InterviewDate {


    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of yyyy-mm-dd with proper month and leap year support";
    //Only valid if yyyy-mm-dd with month and leap year support
    //Retrieve from https://stackoverflow.com/questions/15491894/regex-to-validate-date-formats-dd-mm-yyyy-dd-mm-yyyy
    // -dd-mm-yyyy-dd-mmm-yyyy
    public static final String VALIDATION_REGEX = "^(?:(?:1[6-9]|[2-9]\\d)?\\d{4})(?:(?:(-)(?:0[13578]|1[02])"
            + "\\1(?:31))|(?:(-)(?:0?[13-9]|1[0-2])\\2(?:29|30)))$|^(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]"
            + "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)02\\3(?:29)$|^(?:(?:1[6-9]|[2-9]\\d"
            + ")?\\d{4})(-)(?:(?:0[1-9])|(?:1[0-2]))\\4(?:0[1-9]|1\\d|2[0-8])$";
    public final LocalDate value;

    /**
     * Constructs a {@code InterviewDate}.
     *
     * @param dateString A valid date in String.
     */
    public InterviewDate(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        value = parseLocalDate(dateString);
    }

    /**
     * Returns true if a given string is a valid date string.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.value.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDate // instanceof handles nulls
                && value.equals(((InterviewDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
