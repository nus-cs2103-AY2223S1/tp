package seedu.uninurse.testutil;

import seedu.uninurse.model.medication.Medication;

/**
 * A utility class containing a list of {@code Medication} objects to be used in tests.
 */
public class TypicalMedications {
    public static final Medication MEDICATION_AMOXICILLIN = new Medication("Amoxicillin", "0.5 g every 8 hours");
    public static final Medication MEDICATION_AMPICILLIN = new Medication("Ampicillin", "0.5 IV every 6 hours");
    public static final String TYPICAL_MEDICATION_AMOXICILLIN = "Amoxicillin";
    public static final String TYPICAL_DOSAGE_AMOXICILLIN = "0.5 g every 8 hours";
}
