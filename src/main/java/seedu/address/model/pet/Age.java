package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents the age of a pet. Wraps a single integer.
 * If the age does not match the date of birth, the age will change accordingly.
 */
public class Age {

    public static final String MESSAGE_USAGE =
            "Age should be a non-negative integer, such as 0, 5, and 10";
    private final int value;

    /**
     * Constructs the Age object.
     * @param value The integer to be wrapped.
     */
    public Age(int value) {
        this.value = value;
    }

    /**
     * Calculates the age based on date of birth.
     *
     * @param pet The pet that needs to be updated the age.
     * @return Age in integer
     */
    public static Age generateAge(Pet pet) {
        requireNonNull(pet);
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = pet.getDateOfBirth().getDate();
        requireAllNonNull(currentDate, dateOfBirth);
        int result = Period.between(dateOfBirth, currentDate).getYears();
        return new Age(result);
    }

    /**
     * Gets the age.
     * @return Age in integer
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Age) {
            Age otherAge = (Age) other;
            return value == otherAge.value;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return "Age: " + Integer.toString(value);
    }
}
