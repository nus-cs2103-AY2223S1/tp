package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.Period;

public class Age {

    public static final String MESSAGE_USAGE =
            "Age should be a non-negative integer, such as 0, 5, and 10";
    private final int age;

    public Age(int age) {
        this.age = age;
    }

    public int getPetAge(Pet pet) {
        requireNonNull(pet);
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = pet.getDateOfBirth().getDate();
        requireAllNonNull(currentDate, dateOfBirth);
        return Period.between(dateOfBirth, currentDate).getYears();
    }

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
        return Integer.toString(age);
    }

}
