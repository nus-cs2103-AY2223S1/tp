package seedu.uninurse.model.medication;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.medication.exceptions.MedicationNotFoundException;
import seedu.uninurse.model.medication.exceptions.UnmodifiedMedicationException;

/**
 * Represents a unique list of medications for a particular patient.
 * Two medications with the same name are considered duplicates and are hence, not allowed.
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
        // returns a copy of the internal list to prevent modification to original one
        return new ArrayList<>(internalMedicationList);
    }
}
