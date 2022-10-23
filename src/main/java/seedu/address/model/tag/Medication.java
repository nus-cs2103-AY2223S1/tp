package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Medication of a patient in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMedicationName(String)}
 */
public class Medication {

    public static final String MESSAGE_CONSTRAINTS = "Medication names should be alphanumeric and spaces only";
    public static final String VALIDATION_REGEX = "[A-Za-z0-9\\s]+";
    private static int medicationCount = 0;

    public final String medicationName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param medicationName A valid medication name.
     */
    public Medication(String medicationName) {
        requireNonNull(medicationName);
        checkArgument(isValidMedicationName(medicationName), MESSAGE_CONSTRAINTS);
        this.medicationName = medicationName;
        Medication.medicationCount++;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidMedicationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Returns the number of medications in the database.
     * @return the number of medications in the database.
     */
    public static int getMedicationCount() {
        return medicationCount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && medicationName.equals(((Medication) other).medicationName)); // state check
    }

    @Override
    public int hashCode() {
        return medicationName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + medicationName + ']';
    }

}
