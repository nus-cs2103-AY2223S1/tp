package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.ta.TeachingAssistant;
import seedu.address.model.ta.UniqueTeachingAssistantList;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.UniqueTutorialList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTutorialList tutorials;
    private final UniqueTeachingAssistantList teachingAssistants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tutorials = new UniqueTutorialList();
        teachingAssistants = new UniqueTeachingAssistantList();
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

    /**
     * Replaces the contents of the tutorial list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate tutorials.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials.setTutorials(tutorials);
    }

    /**
     * Replaces the contents of the teaching assistant list with {@code ta}.
     * {@code ta} must not contain duplicate teaching assistants.
     */
    public void setTeachingAssistants(List<TeachingAssistant> ta) {
        this.teachingAssistants.setTeachingAssistants(ta);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());

        setTutorials(newData.getTutorialList());

        setTeachingAssistants(newData.getTeachingAssistantList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
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

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tutorial-level operations

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the ModQuilk.
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    /**
     * Returns true if a tutorial with the same venue and timeslot as {@code tutorial} exists in the ModQuik.
     */
    public boolean hasTutorialClashingWith(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.containsClashingWith(tutorial);
    }

    /**
     * Adds a tutorial to the ModQuik.
     * The tutorial must not already exist in the ModQuik.
     */
    public void addTutorial(Tutorial t) {
        tutorials.add(t);
    }

    //// ta-level operations

    /**
     * Returns true if a teaching assistant with the same identity as {@code ta} exists in the ModQuilk.
     */
    public boolean hasTeachingAssistant(TeachingAssistant ta) {
        requireNonNull(ta);
        return teachingAssistants.contains(ta);
    }

    /**
     * Adds a teaching assistant to the ModQuik.
     * The teaching assistant must not already exist in the ModQuik.
     */
    public void addTeachingAssistant(TeachingAssistant ta) {
        teachingAssistants.add(ta);
    }

    //// util methods

    @Override
    public String toString() {
        String result = persons.asUnmodifiableObservableList().size() + " persons, "
                + tutorials.asUnmodifiableObservableList().size() + " tutorials";
        return result;
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return tutorials.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TeachingAssistant> getTeachingAssistantList() { return teachingAssistants.asUnmodifiableObservableList(); }

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
