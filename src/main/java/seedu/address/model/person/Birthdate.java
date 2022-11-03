package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthdate in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdate(String)}
 */
public class Birthdate {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdate should be in the format of yyyy-mm-dd, it should be a valid date, and it should not be blank.";
    public static final String BIRTHDATE_AFTER_TODAY = "Birthdate should not be in the future.";
    private static final String DATE_FORMAT = "y-M-d";

    public final LocalDate birthdate;


    /**
     * Constructs a {@code Birthdate}.
     *
     * @param birthdate A valid birthdate, in the form dd-mm-yyyy.
     */
    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        checkArgument(isDateInTheFuture(birthdate), BIRTHDATE_AFTER_TODAY);

        this.birthdate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern(DATE_FORMAT));;
    }

    /**
     * Returns true if a given string is a valid birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if given string which is assumed to be a valid date of the format y-M-d
     * is in the future. Meaning the date has not passed, and it is not today.
     * @param test string which is assumed to be a valid date of the format y-M-d
     * @return true of given date is in the future
     */
    public static boolean isDateInTheFuture(String test) {
        LocalDate dateNow = LocalDate.now();
        LocalDate parsedDate = LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return parsedDate.isBefore(dateNow) || parsedDate.isEqual(dateNow);
    }

    @Override
    public String toString() {
        return birthdate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && birthdate.equals(((Birthdate) other).birthdate)); // state check
    }

    @Override
    public int hashCode() {
        return birthdate.hashCode();
    }
}
