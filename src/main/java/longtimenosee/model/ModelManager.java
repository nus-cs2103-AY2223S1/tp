package longtimenosee.model;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.commons.core.LogsCenter;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.exceptions.PersonNotFoundException;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.FinancialAdvisorIncome;
import longtimenosee.model.policy.Policy;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Policy> filteredPolicies;
    private final FinancialAdvisorIncome income;

    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredPolicies = new FilteredList<>(this.addressBook.getPolicyList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        this.income = new FinancialAdvisorIncome();

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    public FinancialAdvisorIncome getIncome() {
        return income;
    }

    @Override
    public void sort(Comparator<Person> comparator) {
        addressBook.sort(comparator);
    }



    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredPolicies.equals(other.filteredPolicies);
    }

    //=========== Policy stuff =============================================================

    @Override
    public boolean hasPolicy(Policy policy) {
        requireNonNull(policy);
        return addressBook.hasPolicy(policy);
    }

    @Override
    public void addPolicy(Policy policy) {
        addressBook.addPolicy(policy);
        updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
    }

    @Override
    public void setPolicy(Policy target, Policy editedPolicy) {
        requireAllNonNull(target, editedPolicy);
        addressBook.setPolicy(target, editedPolicy);
    }
    @Override
    public void deletePolicy(Policy policy) {
        requireNonNull(policy);
        addressBook.removePolicy(policy);
    }

    @Override
    public ObservableList<Policy> getFilteredPolicyList() {
        return filteredPolicies;
    }

    @Override
    public void updateFilteredPolicyList(Predicate<Policy> predicate) {
        requireNonNull(predicate);
        filteredPolicies.setPredicate(predicate);
    }
    //=========== Event stuff =============================================================
    @Override
    public void addEvent(Event e, String personName) throws PersonNotFoundException {
        if (!addressBook.hasPersonByName(personName)) {
            throw new PersonNotFoundException();
        }
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        addressBook.addEvent(e);
    }

    /**
     * Updates the filtered event list
     * @param predicate predicate to filter the list by
     */
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }
    @Override
    public void removeEventsUnderPerson(Person personToDelete) {
        requireNonNull(personToDelete);
        addressBook.removeEventsUnderPerson(personToDelete);
    }

    @Override
    public boolean hasEventOverlap(Event toAdd) {
        return addressBook.checkOverlapEvent(toAdd);
    }
    @Override
    public List<Event> listEventsOverlap(Event toAdd) {
        return addressBook.listEventOverlap(toAdd);
    }

    @Override
    public boolean hasEvent(Event toAdd) {
        requireNonNull(toAdd);
        return addressBook.hasEvent(toAdd);
    }

    @Override
    public void deleteEvent(Event e) {
        requireNonNull(e);
        addressBook.removeEvent(e);
    }
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    /**
     * @param toAdd
     * @return
     */
    @Override
    public List<Event> listEventsSameDay(Event toAdd) {
        requireNonNull(toAdd);
        return addressBook.listEventsSameDay(toAdd);
    }

    @Override
    public List<Event> calendarView() {
        return addressBook.calendarView();
    }

}
