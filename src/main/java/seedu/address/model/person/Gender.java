package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.GenderType.NO_GENDER;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender implements Comparable<Gender> {

    public static final String MESSAGE_CONSTRAINTS = "Gender is an optional field, it should be one of the following"
        + " format: m, f, M, F, male, female, Male, Female";

    private static final Set<String> VALID_GENDERS = new HashSet<>(Arrays.asList("m", "male", "M", "Male",
            "f", "female", "F", "Female", "NA"));
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

    public Gender(GenderType gender) {
        this.value = gender;
    }

    /**
     * Returns if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return VALID_GENDERS.contains(test);
    }

    /**
     * Returns if a given string is a valid gender.
     * This method is an overloaded gender validity check method.
     *
     * @param test string containing gender.
     * @param isNaAllowed boolean value, true represents NA value is considered a valid gender, false otherwise.
     */
    public static boolean isValidGender(String test, Boolean isNaAllowed) {
        if (isNaAllowed) {
            return VALID_GENDERS.contains(test);
        } else {
            return VALID_GENDERS.contains(test) && !test.equals(NO_GENDER.toString());
        }
    }

    /**
     * Returns if a given gender is a valid gender.
     * This method is an overloaded gender validity check method.
     *
     * @param test a gender object.
     * @param isNaAllowed boolean value, true represents NA value is considered a valid gender, false otherwise.
     */
    public static boolean isValidGender(Gender test, Boolean isNaAllowed) {
        if (isNaAllowed) {
            return isNaAllowed;
        } else {
            return !test.equals(Gender.getNoGender());
        }
    }

    public static Gender getNoGender() {
        return new Gender(NO_GENDER);
    }

    /**
     * Compares this object with the given object for order.
     * Genders are ordered in the following manner:
     * NO_GENDER, FEMALE, MALE
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
