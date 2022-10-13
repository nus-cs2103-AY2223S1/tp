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
    private final int age;

    /**
     * Constructs the Age object.
     * @param age The integer to be wrapped.
     */
    public Age(int age) {
        this.age = age;
    }

    /**
     * Calculates the age based on date of birth.
     *
     * @param pet The pet that needs to be updated the age.
     * @return Age in integer
     */
    public int getPetAge(Pet pet) {
        requireNonNull(pet);
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = pet.getDateOfBirth().getDate();
        requireAllNonNull(currentDate, dateOfBirth);
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    /**
     * Gets the age.
     * @return Age in integer
     */
    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Age) {
            Age otherAge = (Age) other;
            return age == otherAge.age;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(age);
    }

    @Override
    public String toString() {
        return "Age: " + Integer.toString(age);
    }
}
