package seedu.uninurse.model;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UninurseBook implements ReadOnlyUninurseBook {
    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public UninurseBook() {}

    /**
     * Creates an UninurseBook using the Persons in the toBeCopied.
     */
    public UninurseBook(ReadOnlyUninurseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with persons.
     * persons must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the patient list with patients.
     * patients must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.persons.setPatients(patients);
    }

    /**
     * Resets the existing data of this UninurseBook with newData.
     */
    public void resetData(ReadOnlyUninurseBook newData) {
        requireAllNonNull(newData);

        setPersons(newData.getPersonList());
        setPatients(newData.getPatientList());
    }

    public void updatePersons() {
        persons.updatePersons();
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity exists in the uninurse book.
     */
    public boolean hasPerson(Person person) {
        requireAllNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the uninurse book.
     * The person must not already exist in the uninurse book.
     */
    public void addPerson(Person person) {
        persons.addPerson(person);
    }

    /**
     * Replaces the given person in the list with editedPerson.
     * person must exist in the uninurse book.
     * The person identity of editedPerson must not be the same as another existing person in the uninurse book.
     */
    public void setPerson(Person person, Person editedPerson) {
        requireAllNonNull(editedPerson);

        persons.setPerson(person, editedPerson);
    }

    /**
     * Adds a patient to the uninurse book.
     * The patient must not already exist in the uninurse book.
     */
    public void addPatient(Patient patient) {
        persons.addPatient(patient);
    }

    /**
     * Replaces the given patient in the list with editedPatient.
     * patient must exist in the uninurse book.
     * The patient identity of editedPatient must not be the same as another existing patient in the uninurse book.
     */
    public void setPatient(Patient patient, Patient editedPatient) {
        requireAllNonNull(editedPatient);

        persons.setPatient(patient, editedPatient);
    }

    /**
     * Removes person from the UninurseBook.
     * person must exist in the uninurse book.
     */
    public void removePerson(Person person) {
        persons.removePerson(person);
    }

    //// patient-level operations

    /**
     * Gets the patient from UniquePersonList if the given person is a patient.
     *
     * @throws PatientNotFoundException if the given person is not a patient.
     */
    public Patient getPatient(Person person) throws PatientNotFoundException {
        return persons.getPatient(person);
    }

    //// list accessors

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return persons.getPatientList();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBook // instanceof handles nulls
                && persons.equals(((UninurseBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
