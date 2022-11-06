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
    private boolean hasLoadedInternship = false;
    private boolean hasLoadedPerson = false;
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
        updateNextPersonId();
        return personIdCounter;
    }

    /**
     * Gets a unique Id to assign to a newly created Internship.
     *
     * @return A unique Id for a newly created Internship.
     */
    public int getNextInternshipId() {
        updateNextInternshipId();
        return internshipIdCounter;
    }

    /**
     * Updates the next PersonId to be 1 + the largest PersonId in the list.
     */
    public void updateNextPersonId() {
        if (!hasLoadedPerson) {
            personIdCounter = -1;
            for (Person p : persons) {
                if (p.getPersonId().id > personIdCounter) {
                    personIdCounter = p.getPersonId().id;
                }
            }
            hasLoadedPerson = true;
        }
        personIdCounter++;
    }

    /**
     * Updates the next InternshipId to be 1 + the largest InternshipId in the list.
     */
    public void updateNextInternshipId() {
        if (!hasLoadedInternship) {
            internshipIdCounter = -1;
            for (Internship i : internships) {
                if (i.getInternshipId().id > internshipIdCounter) {
                    internshipIdCounter = i.getInternshipId().id;
                }
            }
            hasLoadedInternship = true;
        }
        internshipIdCounter++;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setInternships(newData.getInternshipList());

        hasLoadedInternship = false;
        hasLoadedPerson = false;
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in InterNUS.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in InterNUS.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    public Person findPersonById(PersonId personId) {
        return persons.findById(personId);
    }

    @Override
    public String findPersonNameById(PersonId personId) {
        Person p = findPersonById(personId);
        return p == null ? null : p.getName().toString();
    }

    public Internship findInternshipById(InternshipId internshipId) {
        return internships.findById(internshipId);
    }

    @Override
    public String findInternshipNameById(InternshipId internshipId) {
        Internship i = findInternshipById(internshipId);
        return i == null ? null : i.getDisplayName();
    }

    /**
     * Adds a person to InterNUS.
     * The person must not already exist in InterNUS.
     * Updates the personIdCounter to avoid duplicate Ids.
     */
    public void addPerson(Person p) {
        persons.add(p);

        Internship i = findInternshipById(p.getInternshipId());
        if (i != null) {
            Internship linkedI = new Internship(
                    i.getInternshipId(),
                    i.getCompanyName(),
                    i.getInternshipRole(),
                    i.getInternshipStatus(),
                    p.getPersonId(),
                    i.getInterviewDate()
            );
            setInternship(i, linkedI);
        }
    }

    /**
     * Adds an internship to InterNUS.
     * The internship must not already exist in InterNUS.
     * * Updates the internshipIdCounter to avoid duplicate Ids.
     */
    public void addInternship(Internship i) {
        internships.add(i);

        Person p = findPersonById(i.getContactPersonId());
        if (p != null) {
            Person linkedP = new Person(
                    p.getPersonId(),
                    p.getName(),
                    p.getEmail(),
                    p.getPhone(),
                    i.getInternshipId(),
                    p.getTags(),
                    p.getCompany()
            );
            setPerson(p, linkedP);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in InterNUS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in InterNUS.
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
     * {@code key} must exist in InterNUS.
     */
    public void removePerson(Person key) {
        persons.remove(key);

        Internship i = findInternshipById(key.getInternshipId());
        if (i != null) {
            Internship linkedI = new Internship(
                    i.getInternshipId(),
                    i.getCompanyName(),
                    i.getInternshipRole(),
                    i.getInternshipStatus(),
                    null,
                    i.getInterviewDate()
            );
            setInternship(i, linkedI);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in InterNUS.
     */
    public void removeInternship(Internship key) {
        internships.remove(key);

        Person p = findPersonById(key.getContactPersonId());
        if (p != null) {
            Person linkedP = new Person(
                    p.getPersonId(),
                    p.getName(),
                    p.getEmail(),
                    p.getPhone(),
                    null,
                    p.getTags(),
                    p.getCompany()
            );
            setPerson(p, linkedP);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n"
                + internships.asUnmodifiableObservableList().size() + " internships\n";
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
                && persons.equals(((AddressBook) other).persons)
                && internships.equals(((AddressBook) other).internships));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
