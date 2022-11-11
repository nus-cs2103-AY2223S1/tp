package longtimenosee.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.FinancialAdvisorIncome;
import longtimenosee.model.policy.Policy;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_NO_PERSONS = unused -> false;

    Predicate<Policy> PREDICATE_SHOW_ALL_POLICIES = unused -> true;
    Predicate<Policy> PREDICATE_SHOW_NO_POLICIES = unused -> true;

    //TODO: Determine if we should use show all events like this?
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);
    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void pinPerson(Person person);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Sorts the list
     * @param comparator comparator used to sort the list
     */
    void sort(Comparator<Person> comparator);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
    /**
     * Returns true if a policy with the same identity as {@code Policy} exists in the address book.
     */
    boolean hasPolicy(Policy toAdd);

    FinancialAdvisorIncome getIncome();

    /**
     * Adds the policy specified.
     */
    void addPolicy(Policy toAdd);

    /**
     * Deletes the given Policy.
     * The policy must exist in the address book.
     */
    void deletePolicy(Policy toDelete);

    /**
     * Replaces the given Policy {@code target} with {@code editedPolicy}.
     * {@code policy} must exist in the address book.
     * The policy of {@code editedPolicy} must not be the same as another existing policy in the address book.
     */
    void setPolicy(Policy target, Policy editedPolicy);

    /** Returns an unmodifiable view of the filtered policy list */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Updates the filter of the filtered policy list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPolicyList(Predicate<Policy> predicate);

    /**
     * Adds the Event specified.
     */
    void addEvent(Event toAdd, String personName);

    /**
     * Checks if there is an overlapping
     * @param toAdd Event to be added
     * @return
     */
    boolean hasEventOverlap(Event toAdd);

    /**
     * List overlapping events with event passed into param
     * @param toAdd
     * @return
     */
    List<Event> listEventsOverlap(Event toAdd);
    /**
     * Checks if there is already an existing event
     * @param toAdd
     * @return
     */
    boolean hasEvent(Event toAdd);

    /**
     * Deletes an event from event list
     * @param toDelete event to delete
     */
    void deleteEvent(Event toDelete);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Lists events on the same day as an event to add
     * @param toAdd
     * @return
     */
    List<Event> listEventsSameDay(Event toAdd);

    /**
     * Shows upcoming events in the next 7 days
     */
    List<Event> calendarView();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);


    void removeEventsUnderPerson(Person personToDelete);
}
