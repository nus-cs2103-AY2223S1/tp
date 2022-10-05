package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Gender is an optional field, it should be one of the following"
        + " format: m, f, M, F, male, female, Male, Female";

    private static final List<String> VALID_GENDERS = new ArrayList<>(Arrays.asList("m", "male", "M", "Male",
            "f", "female", "F", "Female", "N/A"));
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
