package seedu.uninurse.model.medication;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.medication.exceptions.MedicationNotFoundException;
import seedu.uninurse.model.medication.exceptions.UnmodifiedMedicationException;

/**
 * Represents a unique list of medications for a particular patient.
 * Two medications with the same name and dosage amount are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class MedicationList implements GenericList<Medication> {
    private final List<Medication> internalMedicationList;

    /**
     * Constructs an empty {@code MedicationList}.
     */
    public MedicationList() {
        this.internalMedicationList = new ArrayList<>();
    }

    /**
     * Constructs a {@code MedicationList}.
     * @param medicationList The given list of medications.
     */
    public MedicationList(List<Medication> medicationList) {
        requireNonNull(medicationList);
        this.internalMedicationList = medicationList;
    }

    @Override
    public MedicationList add(Medication medication) {
        requireNonNull(medication);
        if (this.internalMedicationList.contains(medication)) {
            throw new DuplicateMedicationException();
        }

        List<Medication> updatedMedications = new ArrayList<>(internalMedicationList);
        updatedMedications.add(medication);
        return new MedicationList(updatedMedications);
    }

    @Override
    public MedicationList delete(int index) {
        try {
            List<Medication> updatedMedications = new ArrayList<>(internalMedicationList);
            updatedMedications.remove(index);
            return new MedicationList(updatedMedications);
        } catch (IndexOutOfBoundsException e) {
            throw new MedicationNotFoundException();
        }
    }

    @Override
    public MedicationList edit(int index, Medication editedMedication) {
        try {
            List<Medication> updatedMedications = new ArrayList<>(internalMedicationList);
            if (updatedMedications.get(index).equals(editedMedication)) {
                throw new UnmodifiedMedicationException();
            }
            updatedMedications.set(index, editedMedication);
            return new MedicationList(updatedMedications);
        } catch (IndexOutOfBoundsException e) {
            throw new MedicationNotFoundException();
        }
    }

    @Override
    public Medication get(int index) {
        try {
            return internalMedicationList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new MedicationNotFoundException();
        }
    }

    @Override
    public int size() {
        return internalMedicationList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMedicationList.isEmpty();
    }

    @Override
    public List<Medication> getInternalList() {
        return Collections.unmodifiableList(internalMedicationList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalMedicationList.forEach(m -> {
            int index = internalMedicationList.indexOf(m);
            if (index == 0) {
                builder.append(index + 1)
                        .append(". ")
                        .append(m);
            } else {
                builder.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(m);
            }
        });
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicationList // instanceof handles nulls
                && internalMedicationList.equals(((MedicationList) other).internalMedicationList));
    }

    @Override
    public int hashCode() {
        return internalMedicationList.hashCode();
    }
}
