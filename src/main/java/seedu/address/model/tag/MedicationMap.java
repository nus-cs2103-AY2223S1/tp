package seedu.address.model.tag;

import static seedu.address.logic.commands.CountCommand.EACH_MEDICATION_COUNT;
import static seedu.address.logic.commands.CountCommand.MEDICATION_COUNT;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.person.Person;

/**
 * Encapsulates a map of medications and the number of times they have been assigned to patients.
 */
public class MedicationMap {
    public static final String COUNT_BY_MEDICATION = "Patient count by medication:";
    // used internally here to separate medication and count when resetting the address book
    private static final String SEPARATOR = " /-/-/ ";
    private final ObservableMap<String, Integer> medicationMap = FXCollections.observableHashMap();

    /**
     * Adds a medication to the medication map.
     * @param medication The medication to be added.
     */
    public void add(Medication medication) {
        medicationMap.putIfAbsent(medication.medicationName, 0);
        medicationMap.put(medication.medicationName, medicationMap.get(medication.medicationName) + 1);
    }

    /**
     * Removes a medication from the medication map.
     * @param medication The medication to be removed.
     */
    public void remove(Medication medication) {
        if (medicationMap.containsKey(medication.medicationName)) {
            medicationMap.put(medication.medicationName, medicationMap.get(medication.medicationName) - 1);
        }
        medicationMap.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    /**
     * Adds a person's medications to the medication map.
     * @param patient The person whose medications are to be added.
     */
    public void addMedicationsOfPatient(Person patient) {
        patient.getMedications().forEach(this::add);
    }

    /**
     * Clears the medication map of medications allocated to the patient.
     * @param patient The patient to be removed.
     */
    public void clearMedicationsOfPatient(Person patient) {
        patient.getMedications().forEach(this::remove);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(String.format(MEDICATION_COUNT, medicationMap.size()));
        sb.append("\n").append(COUNT_BY_MEDICATION);
        medicationMap.keySet().stream().sorted()
                .forEach(medication ->
                        sb.append("\n   ")
                                .append(String.format(EACH_MEDICATION_COUNT,
                                        medication, medicationMap.get(medication))));
        return sb.toString();
    }

    /**
     * Sets the medication map to the given map.
     */
    public void setStringifiedMedicationMap(String stringifiedMedicationMap) {
        medicationMap.clear();
        String[] medicationArray = stringifiedMedicationMap.split(SEPARATOR);
        if (medicationArray.length == 1 && medicationArray[0].equals("")) {
            return;
        }
        for (int i = 0; i < medicationArray.length; i += 2) {
            medicationMap.put(medicationArray[i], Integer.parseInt(medicationArray[i + 1]));
        }
    }

    /**
     * Returns a stringified version of the medication map.
     */
    public String getStringifiedMedicationMap() {
        StringBuilder sb = new StringBuilder();
        medicationMap.keySet().stream().sorted()
                .forEach(medication ->
                        sb.append(medication)
                                .append(SEPARATOR)
                                .append(medicationMap.get(medication))
                                .append(SEPARATOR));
        return sb.toString();
    }

    /**
     * Updates the medication map to data from the list of persons.
     * @param persons The list of persons to be updated.
     */
    public void updateMedicationMap(List<Person> persons) {
        medicationMap.clear();
        persons.forEach(this::addMedicationsOfPatient);
    }

    /**
     * Returns the medication map size.
     */
    protected int size() {
        return medicationMap.size();
    }
}
