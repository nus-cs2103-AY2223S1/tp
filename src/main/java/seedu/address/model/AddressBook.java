package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.UniqueInternshipList;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    // Internal Id counters
    private int personIdCounter = 0;
    private int internshipIdCounter = 0;

    private final UniquePersonList persons;
    private final UniqueInternshipList internships;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        internships = new UniqueInternshipList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setInternships(List<Internship> internships) {
        this.internships.setInternships(internships);
    }

    /**
     * Gets a unique Id to assign to a newly created Person.
     *
     * @return A unique Id for a newly created Person.
     */
    public int getNextPersonId() {
        return personIdCounter;
    }

    /**
     * Gets a unique Id to assign to a newly created Internship.
     *
     * @return A unique Id for a newly created Internship.
     */
    public int getNextInternshipId() {
        return internshipIdCounter;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setInternships(newData.getInternshipList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    public Person findPersonById(PersonId personId) {
        return persons.findById(personId);
    }

    public Internship findInternshipById(InternshipId internshipId) {
        return internships.findById(internshipId);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     * Updates the personIdCounter to avoid duplicate Ids.
     */
    public void addPerson(Person p) {
        if (p.getPersonId().id + 1 > personIdCounter) {
            personIdCounter = p.getPersonId().id + 1;
        }

        persons.add(p);

        InternshipId internshipId = p.getInternshipId();
        if (internshipId != null) {
            // TODO: Find the associated Internship via Id,
            //  then set the contactPersonId of the Internship to p.getPersonId().
            //  Requires new Set method.
        }
    }

    /**
     * Adds a internship to the address book.
     * The internship must not already exist in the address book.
     * * Updates the internshipIdCounter to avoid duplicate Ids.
     */
    public void addInternship(Internship i) {
        if (i.getInternshipId().id + 1 > internshipIdCounter) {
            internshipIdCounter = i.getInternshipId().id + 1;
        }

        internships.add(i);

        PersonId contactPersonId = i.getContactPersonId();
        if (contactPersonId != null) {
            // TODO: Find the associated Person via Id,
            //  then set internshipId of the Person to i.getInternshipId().
            //  Requires new Set method.
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void removeInternship(Internship key) {
        internships.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internships.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
