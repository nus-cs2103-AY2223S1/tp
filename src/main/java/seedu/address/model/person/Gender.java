package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's gender in the contact list of the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender implements Comparable<Gender> {

    public static final String MESSAGE_CONSTRAINTS = "Gender must be in one of the following"
        + " formats: m / M / male / Male for male, f / F / female / Female for female.";

    private static final Set<String> VALID_GENDERS = new HashSet<>(Arrays.asList("m", "M",
            "f", "F", "male", "Male", "female", "Female"));
    public final GenderType value;


    /**
     * Constructs an {@code Gender}.
     *
     * @param gender  A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.value = GenderType.getGenderType(gender);
    }

    /**
     * Returns if a given string is a valid gender.
     * Accepted gender formats include F, M, f, m, Female, Male, female, male.
     */
    public static boolean isValidGender(String genderToTest) {
        requireNonNull(genderToTest);
        return VALID_GENDERS.contains(genderToTest);
    }
    /**
     * Compares this object with the given object for order.
     * Genders are ordered in the following manner: FEMALE, MALE.
     */
    @Override
    public int compareTo(Gender other) {
        return this.value.compareTo(other.value);
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
