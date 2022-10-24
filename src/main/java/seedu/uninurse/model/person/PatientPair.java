package seedu.uninurse.model.person;

import java.util.Optional;

import javafx.util.Pair;

/**
 * Represents a pair of patients affected in an undoable command.
 */
public class PatientPair {

    private final Pair<Optional<Patient>, Optional<Patient>> patientPair;

    /**
     * Creates a PatientPair using {@code original} and {@code updated}.
     */
    public PatientPair(Patient original, Patient updated) {
        this.patientPair = new Pair<>(Optional.ofNullable(original), Optional.ofNullable(updated));
    }

    /**
     * Creates a PatientPair using {@code original} and {@code updated} wrapped in Optionals.
     */
    public PatientPair(Optional<Patient> original, Optional<Patient> updated) {
        this.patientPair = new Pair<>(original, updated);
    }

    public Optional<Patient> getOriginalPatient() {
        return patientPair.getKey();
    }

    public Optional<Patient> getUpdatedPatient() {
        return patientPair.getValue();
    }
}



