package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender implements Comparable<Gender> {

    public static final String MESSAGE_CONSTRAINTS = "Gender is an optional field, it should be one of the following"
        + " format: m / M / male / Male for male, f / F / female / Female for female.";

    private static final Set<String> VALID_GENDERS = new HashSet<>(Arrays.asList("m", "M",
            "f", "F", "male", "Male", "female", "Female")); //NA is removed from the valid gender list
    public final GenderType value;


    /**
     * Constructs an {@code Gender}.
     *
     * @param gender  A valid gender, accepted formats include F, M, f, m, Female, Male, female, male.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.value = GenderType.getGenderType(gender);
    }

    /**
     * Returns if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return VALID_GENDERS.contains(test);
    }
    /**
     * Compares this object with the given object for order.
     * Genders are ordered in the following manner: FEMALE, MALE
     */
    @Override
    public int compareTo(Gender g) {
        return this.value.compareTo(g.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
