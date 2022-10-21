package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;


/**
 * ExamDate class represents the date of the exam.
 */
public class ExamDate {
    public static final String DATE_CONSTRAINTS =
            "Exam Date should be in dd-mm-yyyy format and must be a valid date.";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    public final String examDate;
    public final String dateWithoutFormatting;


    /**
     * Constructs a {@code ExamDate}.
     *
     * @param date A valid exam date.
     */
    public ExamDate(String date) {
        requireNonNull(date);
        examDate = LocalDate.parse(date, DATE_TIME_FORMATTER).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        dateWithoutFormatting = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        return isValidDateLength(date) && isExistingDate(date) && isNotAPastDate(date);
    }

    public static boolean isValidDateLength(String date) {
        return date.length() >= 10;
    }

    /**
     * Returns true if date inputted exists.
     * @param date A String that represents a date that is in the dd-mm-yyyy format.
     * @return true if date exist, otherwise false.
     */
    public static boolean isExistingDate(String date) {
        try {
            LocalDate.parse(date, DATE_TIME_FORMATTER).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns true if date inputted is not a date before the current date.
     * @param date A String that represents a date that is in the dd-mm-yyyy format.
     * @return true if date is not a past date, otherwise false.
     */
    public static boolean isNotAPastDate(String date) {
        LocalDate d = LocalDate.parse(date, DATE_TIME_FORMATTER);
        return d.compareTo(LocalDate.now()) >= 0;
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
