package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * ExamDate class represents the date of the exam.
 */
public class ExamDate {
    public static final String DATE_CONSTRAINTS =
            "Exam Date should be in YYYY-MM-DD format and must be a valid date.";
    public final String examDate;
    public final String dateWithoutFormatting;


    /**
     * Constructs a {@code ExamDate}.
     *
     * @param date A valid exam date.
     */
    public ExamDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), DATE_CONSTRAINTS);
        examDate = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        dateWithoutFormatting = date;

    }




    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        return date.length() >= 10 && isExistingDate(date);
    }

    /**
     * Returns true if date exists.
     * @param date A String that represents a date that is in YYYY-MM-DD format.
     * @return true if date exist, otherwise false.
     */
    public static boolean isExistingDate(String date) {
        try {
            LocalDate.parse(date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return examDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExamDate // instanceof handles nulls
                && examDate.equals(((ExamDate) other).examDate)); // state check
    }

    @Override
    public int hashCode() {
        return examDate.hashCode();
    }
}
