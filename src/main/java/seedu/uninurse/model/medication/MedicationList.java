package seedu.uninurse.model.medication;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.ModificationType;
import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.medication.exceptions.MedicationNotFoundException;

/**
 * Represents a unique list of medications for a particular patient.
 * Two medications with the same name and dosage amount are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class MedicationList implements GenericList<Medication> {
    private final List<Medication> internalMedicationList;

    /**
     * Constructs an empty MedicationList.
     */
    public MedicationList() {
        this.internalMedicationList = new ArrayList<>();
    }

    /**
     * Constructs a MedicationList.
     * @param medicationList The given list of medications.
     */
    public MedicationList(List<Medication> medicationList) {
        requireAllNonNull(medicationList);
        this.internalMedicationList = medicationList;
    }

    @Override
    public MedicationList add(Medication medication) {
        requireAllNonNull(medication);
        if (internalMedicationList.contains(medication)) {
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
        requireAllNonNull(editedMedication);
        try {
            List<Medication> updatedMedications = new ArrayList<>(internalMedicationList);
            updatedMedications.set(index, editedMedication);

            if (internalMedicationList.contains(editedMedication)) {
                throw new DuplicateMedicationException();
            }

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
    public List<ListModificationPair> getDiff(GenericList<Medication> otherMedicationList) {
        List<ListModificationPair> listModificationPairs = new ArrayList<>();

        if (equals(otherMedicationList)) {
            return listModificationPairs;
        }

        if (size() == 0 && otherMedicationList.size() == 1) {
            listModificationPairs.add(new ListModificationPair(ModificationType.ADD, 0));
            return listModificationPairs;
        }

        if (size() == 1 && otherMedicationList.size() == 0) {
            listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, 0));
            return listModificationPairs;
        }

        int pointer = 0;
        while (get(pointer).equals(otherMedicationList.get(pointer))) {
            pointer++;
            if (pointer == size() || pointer == otherMedicationList.size()) {
                if (size() < otherMedicationList.size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.ADD, pointer));
                } else if (otherMedicationList.size() < size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, pointer));
                }
                break;
            }
        }
        listModificationPairs.add(new ListModificationPair(ModificationType.EDIT, pointer));
        return listModificationPairs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        internalMedicationList.forEach(m -> {
            int index = internalMedicationList.indexOf(m);
            if (index == 0) {
                sb.append(index + 1)
                        .append(". ")
                        .append(m);
            } else {
                sb.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(m);
            }
        });
        return sb.toString();
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
