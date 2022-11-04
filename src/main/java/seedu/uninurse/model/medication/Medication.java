package seedu.uninurse.model.medication;

import static seedu.uninurse.commons.util.AppUtil.checkArgument;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

/**
 * Represents a Patient's required medication type and dosage
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

    private final String medicationType;
    private final String medicationDosage;

    /**
     * Constructs a Medication.
     *
     * @param medicationType A valid medication type.
     * @param medicationDosage A valid dosage amount.
     */
    public Medication(String medicationType, String medicationDosage) {
        requireAllNonNull(medicationType, medicationDosage);
        checkArgument(isValidMedication(medicationType, medicationDosage), MESSAGE_CONSTRAINTS);
        this.medicationType = medicationType;
        this.medicationDosage = medicationDosage;
    }

    /**
     * Returns true if a given string is a valid medication.
     */
    public static boolean isValidMedication(String medicationType, String medicationDosage) {
        return medicationType.matches(MEDICATION_VALIDATION_REGEX) && medicationDosage.matches(DOSAGE_VALIDATION_REGEX);
    }

    /**
     * Returns true if given optional strings make a part of a valid medication.
     * At least one of the given optional strings cannot be empty.
     */
    public static boolean isValidMedication(Optional<String> optionalMedicationType,
            Optional<String> optionalMedicationDosage) {
        return (optionalMedicationType.isPresent() || optionalMedicationDosage.isPresent())
                && optionalMedicationType.map(type -> type.matches(MEDICATION_VALIDATION_REGEX)).orElse(true)
                && optionalMedicationDosage.map(dosage -> dosage.matches(DOSAGE_VALIDATION_REGEX)).orElse(true);
    }

    public String getType() {
        return medicationType;
    }

    public String getDosage() {
        return medicationDosage;
    }

    @Override
    public String toString() {
        return medicationType + " | " + medicationDosage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && medicationType.equals(((Medication) other).medicationType)
                && medicationDosage.equals(((Medication) other).medicationDosage)); // state check
    }

    @Override
    public int hashCode() {
        return medicationType.hashCode();
    }
}
