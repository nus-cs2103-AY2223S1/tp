package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthdate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdate(String)}
 */
public class Birthdate {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DISPLAYED_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    public static final String MESSAGE_CONSTRAINTS = "Birthdate has to be of format dd-MM-yyyy!";
    private final LocalDate birthdate;

    /**
     * Constructs a {@code Birthdate}.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        this.birthdate = LocalDate.parse(birthdate, DATE_FORMAT);
    }

    /**
     * Returns true if the given birthdate is valid.
     *
     * @param testDate Birthdate to be tested.
     * @return true when the given birthdate is valid.
     */
    public static boolean isValidBirthdate(String testDate) {
        try {
            LocalDate.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
