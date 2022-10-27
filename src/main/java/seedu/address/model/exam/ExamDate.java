package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * ExamDate class represents the date of the exam.
 */
public class ExamDate {
    public static final String DATE_CONSTRAINTS =
            "Exam Date should be in dd-mm-yyyy format and a valid date where dd is from 1 to 31, mm is from 1 to 12";
    public static final String DATE_FORMAT_CONSTRAINTS =
            "Exam Date should be in dd-mm-yyyy format.";
    public static final String NOT_A_PAST_DATE_CONSTRAINTS =
            "Exam Date inputted should not be a past date";
    public static final String VALID_DATE_CONSTRAINTS =
            "Exam Date should be a valid date";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    public final String examDate;


    /**
     * Constructs a {@code ExamDate}.
     *
     * @param date A valid exam date.
     */
    public ExamDate(String date) {
        requireNonNull(date);
        examDate = date;
    }


    /**
     * Checks if the format given for the date is valid.
     *
     * @param date The date provided.
     * @return true if the date has the correct format; else return false.
     */
    public static boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if format is in dd-mm-yyyy where dd is between 01 to 31,
     * mm is between 01 to 12, yyyy is between 0000 to 9999.
     * @param date The date provided.
     * @return true if date is in dd-mm-yyyy format.
     */
    public static boolean isCorrectDateFormat(String date) {
        try {
            String[] a = date.split("-");
            Integer days = Integer.parseInt(a[0]);
            Integer month = Integer.parseInt(a[1]);
            Integer year = Integer.parseInt(a[2]);
            if (year >= 0000 && year <= 9999 && month >= 01 && month
                    <= 12 && days >= 01 && days <= 31 && isValidDateLength(date)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }


    public static boolean isValidDateLength(String date) {
        return date.length() == 10;
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
