package seedu.address.model.category;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Gender;

/**
 * Represents a Category in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCategoryName(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names can be only N or P, N for nurse and P for patient";
    public static final String NURSE_SYMBOL = "N";
    public static final String PATIENT_SYMBOL = "P";
    public static final String VALIDATION_REGEX = "[" + NURSE_SYMBOL + "|" + PATIENT_SYMBOL + "]";

    public final String categoryName;

    /**
     * Constructs a {@code Category}.
     *
     * @param categoryName A valid category name.
     */
    public Category(String categoryName) {
        requireNonNull(categoryName);
        checkArgument(isValidCategoryName(categoryName), MESSAGE_CONSTRAINTS);
        this.categoryName = categoryName;
    }

    /**
     * Returns true if a given string is a valid category name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.category.Category // instanceof handles nulls
                && categoryName.equals(((seedu.address.model.category.Category) other).categoryName)); // state check
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }


    public String toString() {
        return categoryName;
    }

    /**
     * Checks if the two category objects are equal, ignoring case.
     * @param other other object to be checked against
     * @return true if both categories are the same, false otherwise
     */
    public boolean equalsIgnoreCase(Object other) {
        return other == this
                || (other instanceof Category)
                && categoryName.equalsIgnoreCase(((Category) other).categoryName);
    }
}
