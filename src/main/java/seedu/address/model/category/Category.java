package seedu.address.model.category;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Category in the address book.
 * Guarantees: immutable; name is valid as declared in
 * {@link #isValidCategoryName(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names can be only N for nurse, "
            + "P for patient, D for physician or K for next of kin.";
    public static final String NURSE_SYMBOL = "N";
    public static final String PATIENT_SYMBOL = "P";
    public static final String PHYSICIAN_SYMBOL = "D";
    public static final String NEXTOFKIN_SYMBOL = "K";
    public static final String VALIDATION_REGEX = "[" + NURSE_SYMBOL + "|" + PATIENT_SYMBOL + "|"
            + PHYSICIAN_SYMBOL + "|" + NEXTOFKIN_SYMBOL + "]";
    public static final ArrayList<String> COMMON_NURSE_MISSPELLINGS = new ArrayList<>(
            List.of("nurse", "nurses", "n"));
    public static final ArrayList<String> COMMON_PATIENT_MISSPELLINGS = new ArrayList<>(
            List.of("patient", "patients", "p"));

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
     * Catches and formats any common misspellings as defined in the common
     * misspelling constant of nurses and patients
     *
     * @param test The String to be tested
     * @return THe nurse or patient symbol if it is a misspelling and the original
     *         text otherwise
     */
    public static String formatMisspelling(String test) {
        if (COMMON_NURSE_MISSPELLINGS.contains(test.trim().toLowerCase())) {
            return NURSE_SYMBOL;
        }
        if (COMMON_PATIENT_MISSPELLINGS.contains(test.trim().toLowerCase())) {
            return PATIENT_SYMBOL;
        }
        return test;
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
     *
     * @param other other object to be checked against
     * @return true if both categories are the same, false otherwise
     */
    public boolean equalsIgnoreCase(Object other) {
        return other == this
                || (other instanceof Category)
                        && categoryName.equalsIgnoreCase(((Category) other).categoryName);
    }

    public boolean isNurse() {
        return this.categoryName.equals(NURSE_SYMBOL);
    }

    public boolean isPatient() {
        return this.categoryName.equals(PATIENT_SYMBOL);
    }

    public boolean isPhysician() {
        return this.categoryName.equals(PHYSICIAN_SYMBOL);
    }

    public boolean isNextOfKin() {
        return this.categoryName.equals(NEXTOFKIN_SYMBOL);
    }

    public String toFormattedString() {
        return String.format("Category: %s;", categoryName);
    }
}
