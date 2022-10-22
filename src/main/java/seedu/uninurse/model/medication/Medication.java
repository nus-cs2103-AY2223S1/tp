package seedu.uninurse.model.medication;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's required medication type and dosage
 * Guarantees: immutable; is valid as declared in {@link #isValidMedication(String)}
 */
public class Medication {
    public static final String MESSAGE_CONSTRAINTS =
            "Medication type can take any values and should not be blank.\n"
            + "Dosage amount should only contain alphanumeric characters, "
            + "decimal points and spaces and should not be blank";

    /*
     * The first character of the medication type and dosage amount must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String MEDICATION_VALIDATION_REGEX = "[^\\s].*";
    public static final String DOSAGE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}. ]*";

    public final String medicationType;
    public final String dosage;

    /**
     * Constructs a {@code Medication}.
     *
     * @param medicationType A valid medication type.
     * @param dosage A valid dosage amount.
     */
    public Medication(String medicationType, String dosage) {
        requireNonNull(medicationType);
        requireNonNull(dosage);
        checkArgument(isValidMedication(medicationType, dosage), MESSAGE_CONSTRAINTS);
        this.medicationType = medicationType;
        this.dosage = dosage;
    }

    /**
     * Returns true if a given string is a valid medication.
     */
    public static boolean isValidMedication(String medicationType, String dosage) {
        return medicationType.matches(MEDICATION_VALIDATION_REGEX) && dosage.matches(DOSAGE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return medicationType + " \\| " + dosage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && medicationType.equals(((Medication) other).medicationType)
                && dosage.equals(((Medication) other).dosage)); // state check
    }

    @Override
    public int hashCode() {
        return medicationType.hashCode();
    }
}
