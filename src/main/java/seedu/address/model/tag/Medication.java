package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.commands.CountCommand.EACH_MEDICATION_COUNT;
import static seedu.address.logic.commands.CountCommand.MEDICATION_COUNT;

import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.Model;

/**
 * Represents a Medication of a patient in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMedicationName(String)}
 */
public class Medication {

    public static final String MESSAGE_CONSTRAINTS = "Medication names should be alphanumeric and spaces only";
    public static final String COUNT_BY_MEDICATION = "Patient count by medication:";
    public static final String VALIDATION_REGEX = "[A-Za-z0-9\\s]+";
    private static final ObservableMap<String, Integer> medicationMap = FXCollections.observableHashMap();
    public final String medicationName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param medicationName A valid medication name.
     */
    public Medication(String medicationName) {
        requireNonNull(medicationName);
        checkArgument(isValidMedicationName(medicationName), MESSAGE_CONSTRAINTS);
        this.medicationName = medicationName.toLowerCase(Locale.ENGLISH);
        Medication.medicationMap.putIfAbsent(medicationName.toLowerCase(Locale.ENGLISH), 0);
        Medication.medicationMap.put(medicationName.toLowerCase(Locale.ENGLISH),
                Medication.medicationMap.get(medicationName.toLowerCase(Locale.ENGLISH)) + 1);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidMedicationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns all the medications in the database, and the number of times they have been used.
     */
    public static String getMedicationMap(Model model) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(String.format(MEDICATION_COUNT, medicationMap.size()));
        sb.append("\n").append(COUNT_BY_MEDICATION);
        medicationMap
                .keySet()
                .stream()
                .sorted()
                .forEach(medication -> sb
                        .append("\n   ")
                        .append(String.format(EACH_MEDICATION_COUNT, medication,
                                medicationMap.get(medication))));
        return sb.toString();
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
