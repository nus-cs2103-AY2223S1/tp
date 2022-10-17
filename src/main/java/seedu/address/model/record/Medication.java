package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class encapsulating prescribed medication as part of medical records.
 */
public class Medication {
    public static final String MESSAGE_CONSTRAINTS = "Medication field cannot be empty!";
    public static final String MESSAGE_NO_MEDICATION_GIVEN = "No medication was prescribed!";
    public final String medicationName;

    /**
     * Returns Medication object of input string.
     *
     * @param medication Input string.
     */
    private Medication(String medication) {
        requireNonNull(medication);
        checkArgument(isValidMedication(medication), MESSAGE_CONSTRAINTS);
        this.medicationName = medication;
    }

    /**
     * Empty constructor.
     */
    private Medication() {
        this.medicationName = MESSAGE_NO_MEDICATION_GIVEN;
    }

    /**
     * Factory method of the Medication class.
     * Also used to parse JSON Storage file.
     *
     * @param in String to be read.
     * @return Medication object corresponding to the input string.
     */
    public static Medication of(String in) {
        if (in.equals(MESSAGE_NO_MEDICATION_GIVEN)) {
            return new Medication();
        } else {
            return new Medication(in);
        }
    }

    /**
     * Check if an input string medication is valid.
     * Input string is valid if it is non-empty.
     *
     * @param in String to be tested.
     * @return True if input string is valid. False otherwise.
     */
    public static boolean isValidMedication(String in) {
        return !in.isEmpty();
    }

    @Override
    public String toString() {
        return this.medicationName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && isMedicationNamesEqual(((Medication) other).medicationName)// state check
                );
    }

    /**
     * Determine if two medication names are equal, regardless of lettercase
     *
     * @param other Other medication name to check.
     * @return True if both names are equal. False otherwise.
     */
    private boolean isMedicationNamesEqual(String other) {
        String name = medicationName.toLowerCase();
        String otherName = other.toLowerCase();

        return name.equals(otherName);
    }

    @Override
    public int hashCode() {
        return medicationName.hashCode();
    }

}
