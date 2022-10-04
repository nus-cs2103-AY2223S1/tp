package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMealType(String)}
 */
public class MealType {


    public static final String MESSAGE_CONSTRAINTS =
            "Meal types should only be either Breakfast, Lunch or Dinner";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public MealType(String phone) {
        requireNonNull(phone);
        checkArgument(isValidMealType(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidMealType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MealType // instanceof handles nulls
                && value.equals(((MealType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
