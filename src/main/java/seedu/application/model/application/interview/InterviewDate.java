package seedu.application.model.application.interview;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.application.model.CommonRegex;

/**
 * Represents the Date of Interview for the specific Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class InterviewDate {


    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of yyyy-mm-dd with proper month and leap year support";
    public static final DateTimeFormatter COMMAND_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
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
     * Returns true if a given string contains another prefix or prefix, argument pair.
     */
    public static boolean hasAPrefix(String test) {
        String[] filteredTest = test.split(" ");
        if (filteredTest.length == 2) {
            return filteredTest[1].matches(CommonRegex.VALIDATION_REGEX_FOR_EXTRA_PREFIX_DATE_TIME);
        }
        return false; //error not cause by extra prefix
    }

    /**
     * Returns true if a given string is a valid date string.
     */
    public static boolean isValidDate(String test) {
        try {
            COMMAND_DATE_FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString, COMMAND_DATE_FORMATTER);
    }

    /**
     * Returns the command string that corresponds to this {@code Date}.
     *
     * @return The command string that corresponds to this {@code Date}.
     */
    public String toCommandString() {
        return COMMAND_DATE_FORMATTER.format(value);
    }

    @Override
    public String toString() {
        return this.value.format(DISPLAY_DATE_FORMATTER);
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
