package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's birthdate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateFormat(String)}
 */
public class Birthdate {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DISPLAYED_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Birthdates have to be of format dd-MM-yyyy! "
            + "Please also ensure this is a valid date!";
    public static final String MESSAGE_FUTURE_DATE = "Birthdates must not be later than the current date!";
    private final LocalDate birthdate;

    /**
     * Constructs a {@code Birthdate}.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidDateFormat(birthdate), MESSAGE_INVALID_DATE_FORMAT);
        checkArgument(!isFutureDate(birthdate), MESSAGE_FUTURE_DATE);
        this.birthdate = LocalDate.parse(birthdate, DATE_FORMAT);
    }

    /**
     * Returns true if the given birthdate is in the valid format.
     *
     * @param testDate Birthdate to be tested.
     * @return true when the given birthdate is in the valid format.
     */
    public static boolean isValidDateFormat(String testDate) {
        try {
            LocalDate.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the given birthdate is after the current date.
     *
     * @param testDate Birthdate to be tested.
     * @return true when the given birthdate is after the current date.
     */
    public static boolean isFutureDate(String testDate) {
        LocalDate birthdate = LocalDate.parse(testDate, DATE_FORMAT);
        LocalDate currDate = LocalDate.now();
        return birthdate.isAfter(currDate);
    }

    /**
     * Calculates and returns the age of a Person with this birthdate.
     *
     * @return the age of a Person with this birthdate.
     */
    public int calculateAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * Returns the String representation of the birthdate when displayed in the GUI.
     *
     * @return String representation of birthdate, when displayed in the GUI.
     */
    public String toDisplayFormat() {
        return this.birthdate.format(DISPLAYED_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return this.birthdate.format(DATE_FORMAT);
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
