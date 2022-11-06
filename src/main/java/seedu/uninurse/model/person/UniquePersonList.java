package seedu.uninurse.model.person;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.exceptions.DuplicatePersonException;
import seedu.uninurse.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and
 * updating of persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added
 * or updated is unique in terms of identity in the UniquePersonList. However, the removal of a person uses
 * Person#equals(Object) so as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {
    private final ObservableList<Person> internalPersonList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalPersonList);
    private final ObservableList<Patient> internalPatientList = FXCollections.observableArrayList();
    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireAllNonNull(toCheck);
        return internalPersonList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void addPerson(Person toAdd) {
        requireAllNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalPersonList.add(toAdd);
    }

    /**
     * Replaces the person in the list with editedPerson.
     * person must exist in the list.
     * The person identity of editedPerson must not be the same as another existing person in the list.
     */
    public void setPerson(Person person, Person editedPerson) {
        requireAllNonNull(person, editedPerson);

        int index = internalPersonList.indexOf(person);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!person.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalPersonList.set(index, editedPerson);
    }

    /**
     * Adds a patient to the list.
     * The patient must not already exist in the list.
     */
    public void addPatient(Patient patient) {
        requireAllNonNull(patient);
        addPerson(patient);
        internalPatientList.add(patient);
    }

    /**
     * Replaces the patient in the list with editedPatient.
     * patient must exist in the list.
     * The patient identity of editedPatient must not be the same as another existing patient in the list.
     */
    public void setPatient(Patient patient, Patient editedPatient) {
        requireAllNonNull(patient, editedPatient);
        setPerson(patient, editedPatient);

        int index = internalPatientList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        if (!patient.isSamePerson(editedPatient) && contains(editedPatient)) {
            throw new DuplicatePersonException();
        }

        internalPatientList.set(index, editedPatient);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void removePerson(Person toRemove) {
        requireAllNonNull(toRemove);
        if (!internalPersonList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
        internalPatientList.remove(toRemove);
    }

    public void setPersons(UniquePersonList replacement) {
        requireAllNonNull(replacement);
        internalPersonList.setAll(replacement.internalPersonList);
        internalPatientList.setAll(replacement.internalPatientList);
    }

    /**
     * Replaces the contents of the person list with persons.
     * persons must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalPersonList.setAll(persons);
    }

    /**
     * Replaces the contents of the patient list with patients.
     * patients must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!patientsAreUnique(patients)) {
            throw new DuplicatePersonException();
        }

        internalPatientList.setAll(patients);
    }

    /**
     * Returns the patient from the patient list if the given person is a patient.
     *
     * @throws PatientNotFoundException if the given person is not in the patient list.
     */
    public Patient getPatient(Person person) throws PatientNotFoundException {
        requireAllNonNull(person);

        int index = internalPatientList.indexOf(person);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        return internalPatientList.get(index);
    }

    /**
     * Returns the backing list as an unmodifiable ObservableList.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<Patient> getPatientList() {
        return FXCollections.unmodifiableObservableList(internalPatientList);
    }

    public void updatePersons() {
        internalPersonList.forEach(p -> p.update());
    }

    /**
     * Returns true if persons contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if patients contains only unique patients.
     */
    private boolean patientsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePerson(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalPersonList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                && internalPersonList.equals(((UniquePersonList) other).internalPersonList)
                && internalPatientList.equals(((UniquePersonList) other).internalPatientList));
    }

    @Override
    public int hashCode() {
        return internalPersonList.hashCode();
    }
}
