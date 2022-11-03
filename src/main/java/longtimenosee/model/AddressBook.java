package longtimenosee.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.UniqueEventList;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.UniquePersonList;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.UniquePolicyList;



/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePolicyList policies;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        policies = new UniquePolicyList();
        events = new UniqueEventList();

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
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setPolicies(List<Policy> policies) {
        this.policies.setPolicies(policies);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPolicies(newData.getPolicyList());
        setEvents(newData.getEventList());
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
     * Additional defensive programming check to remove events in addressBook under person.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        events.removeEventsUnderPerson(key);

    }

    /**
     * Sorts the address book
     * @param comparator comparator used to sort the address book
     */
    public void sort(Comparator<Person> comparator) {
        persons.sort(comparator);
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


    //policy level operations

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in the address book.
     */
    public boolean hasPolicy(Policy policy) {
        requireNonNull(policy);
        return policies.contains(policy);
    }

    /**
     * Adds a policy to the address book.
     * The policy must not already exist in the address book.
     */
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    @Override
    public ObservableList<Policy> getPolicyList() {
        return policies.asUnmodifiableObservableList();
    }

    public void removePolicy(Policy policy) {
        policies.remove(policy);
    }

    /**
     * Replaces the given policy {@code target} in the list with {@code editedPolicy}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPolicy} must not be the same as another existing policy in the address book.
     */
    public void setPolicy(Policy target, Policy editedPolicy) {
        requireNonNull(editedPolicy);

        policies.setPolicy(target, editedPolicy);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && policies.equals(((AddressBook) other).policies)
                && events.equals(((AddressBook) other).events));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + policies.hashCode();
    }

    /**
     * Checks the existing personList if a person has a name which matches the name given.
     * @param personName
     * @return
     */
    public boolean hasPersonByName(String personName) {
        return persons.hasPersonByName(personName);
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public boolean hasEvent(Event toAdd) {
        return events.hasEvent(toAdd);
    }
    public void removeEvent(Event e) {
        events.deleteEvent(e);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    /**
     * Replaces the contents of the Event list with {@code events}.
     * {@code events} must not contain duplicate clients.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    public boolean checkOverlapEvent(Event toAdd) {
        return events.overlaps(toAdd);
    }

    /**
     * List overlapping events with event passed into parameter
     * @param toAdd
     * @return list of overlapping events.
     */
    public List<Event> listEventOverlap(Event toAdd) {
        return events.listEventOverlap(toAdd);
    }
    /**
     * List events on the same day with event passed into parameter
     * @param toAdd
     * @return list of overlapping events.
     */
    public List<Event> listEventsSameDay(Event toAdd) {
        return events.listEventSameDay(toAdd);
    }

    /**
     * Lists upcoming events in the next 7 days
     */
    public List<Event> calendarView() {
        return events.calendarView();
    }
    /**
     * Removes events associated with a given person.
     * The person should no longer exist in the model
     */

    public void removeEventsUnderPerson(Person person) {
        events.removeEventsUnderPerson(person);
    }

}
