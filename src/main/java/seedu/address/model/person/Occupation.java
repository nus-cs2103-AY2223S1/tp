package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's occupation in the address book.
 */
public class Occupation {
    enum Occupations {
        STUDENT,
        TA,
        PROFESSOR,
        NONE
    }
    public static final String MESSAGE_CONSTRAINTS =
            "Valid occupations are {TA, STUDENT, PROFESSOR}";

    /*
     * The first character of the occupation must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String s;

    public final Occupations o;

    /**
     * Constructs a {@code Occupation}.
     *
     * @param occupation A valid Occupation.
     */
    public Occupation(String occupation) {
        requireNonNull(occupation);
        checkArgument(isValidOccupation(occupation), MESSAGE_CONSTRAINTS);
        this.s = occupation;
        this.o = checkOccupation(occupation);
    }

    /**
     * Returns true if input is a valid occupation
     * @param test Input to be tested
     * @return True for valid input.
     */
    public static boolean isValidOccupation(String test) {
        for (Occupations o : Occupations.values()) {
            if (test.equalsIgnoreCase(o.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns Occupation of input string.
     * @param test Input to be returned in the form of Occupation
     * @return True for valid input.
     */
    public static Occupations checkOccupation(String test) {
        for (Occupations o : Occupations.values()) {
            if (test.equalsIgnoreCase(o.toString())) {
                return o;
            }
        }
        return Occupations.NONE;
    }

    /**
     * Returns true if the value is the deleted placeholder.
     */
    public boolean isDeleted() {
        return this.s.equals("NONE");
    }

    public String getString() {
        return this.o.toString();
    }

    @Override
    public String toString() {
        return o.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Occupation // instanceof handles nulls
                && o.equals(((Occupation) other).o)); // state check
    }

    @Override
    public int hashCode() {
        return o.hashCode();
    }
}
