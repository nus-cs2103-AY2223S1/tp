package seedu.uninurse.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.uninurse.model.person.Patient;

/**
 * Represents the patients added and deleted in an undoable command.
 */
public class PatientListTracker {

    /**
     * List of patients that were added to reach the current snapshot.
     */
    private final Optional<List<Patient>> addedPatients;

    /**
     * List of patients that were deleted to reach the current snapshot.
     */
    private final Optional<List<Patient>> deletedPatients;

    /**
     * Creates a PatientListTracker with no patients.
     */
    public PatientListTracker() {
        this.addedPatients = Optional.empty();
        this.deletedPatients = Optional.empty();
    }

    /**
     * Creates a PatientListTracker using {@code addedPatients} and {@code deletedPatients}.
     */
    public PatientListTracker(List<Patient> addedPatients, List<Patient> deletedPatients) {
        this.addedPatients = Optional.ofNullable(addedPatients);
        this.deletedPatients = Optional.ofNullable(deletedPatients);
    }

    /**
     * Creates a PatientListTracker using {@code addedPatient} and {@code deletedPatient}.
     */
    public PatientListTracker(Patient addedPatient, Patient deletedPatient) {
        this.addedPatients = (addedPatient == null) ? Optional.empty() : Optional.of(Arrays.asList(addedPatient));
        this.deletedPatients = (deletedPatient == null) ? Optional.empty() : Optional.of(Arrays.asList(deletedPatient));
    }

    public Optional<List<Patient>> getAddedPatients() {
        return this.addedPatients;
    }

    public Optional<List<Patient>> getDeletedPatients() {
        return this.deletedPatients;
    }

    public boolean isSinglePatient() {
        return addedPatients.map(list -> (list.size() <= 1)).orElse(false)
                || deletedPatients.map(list -> (list.size() <= 1)).orElse(false);
    }

    public boolean isMultiplePatients() {
        return addedPatients.map(list -> (list.size() > 1)).orElse(false)
                || deletedPatients.map(list -> (list.size() > 1)).orElse(false);
    }

    public boolean isAdd() {
        return addedPatients.map(list -> (list.size() > 0)).orElse(false)
                && deletedPatients.map(list -> (list.size() == 0)).orElse(true);
    }

    public boolean isDelete() {
        return deletedPatients.map(list -> (list.size() > 0)).orElse(false)
                && addedPatients.map(list -> (list.size() == 0)).orElse(true);
    }

    public boolean isEdit() {
        return addedPatients.map(list -> (list.size() == 1)).orElse(false)
                && deletedPatients.map(list -> (list.size() == 1)).orElse(false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientListTracker)) {
            return false;
        }

        // state check
        PatientListTracker tracker = (PatientListTracker) other;
        return this.addedPatients.equals(tracker.addedPatients)
                && this.deletedPatients.equals(tracker.deletedPatients);
    }
}



