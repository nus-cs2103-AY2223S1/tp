package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Gender can only be F or M, F for female, M for male";
    public static final String FEMALE_SYMBOL = "F";
    public static final String MALE_SYMBOL = "M";
    private static final ArrayList<String> COMMON_FEMALE_MISSPELLINGS = new ArrayList<>(
            List.of("female", "females", "f"));
    private static final ArrayList<String> COMMON_MALE_MISSPELLINGS = new ArrayList<>(
            List.of("male", "males", "m"));

    /**
     * The gender can only be F or M.
     */
    public static final String VALIDATION_REGEX = "[" + FEMALE_SYMBOL + "|" + MALE_SYMBOL + "]";

    public final String gender;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
    }

    /**
     * Catches and formats any common misspellings as defined in the common
     * misspelling constant of females and males
     *
     * @param test The String to be tested
     * @return The female or male symbol if it is a misspelling and the original
     *         text otherwise
     */
    public static String formatMisspelling(String test) {
        if (COMMON_FEMALE_MISSPELLINGS.contains(test.trim().toLowerCase())) {
            return FEMALE_SYMBOL;
        }
        if (COMMON_MALE_MISSPELLINGS.contains(test.trim().toLowerCase())) {
            return MALE_SYMBOL;
        }
        return test;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                        && gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }

    /**
     * Checks if the two gender objects are equal, ignoring case.
     *
     * @param other other object to be checked against
     * @return true if both genders are the same, false otherwise
     */
    public boolean equalsIgnoreCase(Object other) {
        return other == this
                || (other instanceof Gender)
                        && gender.equalsIgnoreCase(((Gender) other).gender);
    }
}
