package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.SamePersonPredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final CommandHistory commandHistory;
    private final ObservableList<CalendarEvent> calendarEventList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyCommandHistory commandHistory) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.commandHistory = new CommandHistory(commandHistory);
        this.calendarEventList = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new CommandHistory());
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
    public boolean hasPersonWithSameAppointmentDateTime(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasPersonWithSameAppointmentDateTime(appointment);
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

    @Override
    public void sortPerson(Comparator<Person> comparator) {
        addressBook.sortPersons(comparator);
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
    public void updateFilteredPersonList(List<? extends Predicate<Person>> predicates) {
        requireNonNull(predicates);
        int size = predicates.size();
        HashSet<Person> newPersons = new HashSet<>();
        for (int i = 0; i < size; i++) {
            ObservableList<Person> test = FXCollections.observableArrayList();
            filteredPersons.forEach(test::add);
            FilteredList<? extends Person> filteredPersonsCopy = new FilteredList<>(test);
            filteredPersonsCopy.setPredicate(predicates.get(i));
            filteredPersonsCopy.stream().forEach(newPersons::add);
        }
        filteredPersons.setPredicate(new SamePersonPredicate(newPersons));
    }

    @Override
    public ObservableList<CalendarEvent> getFilteredCalendarEventList() {
        ObservableList<Person> lastShownList = this.filteredPersons;
        ObservableList<CalendarEvent> calendarEventList = getCalendarEventList(lastShownList);
        return calendarEventList;
    }

    private ObservableList<CalendarEvent> getCalendarEventList(ObservableList<Person> lastShownList) {
        calendarEventList.clear();
        lastShownList.stream().map(x -> x.getCalendarEvents()).forEach(e -> calendarEventList.addAll(e));
        return calendarEventList;
    }

    @Override
    public void updateCalendarEventList() {
        getCalendarEventList(filteredPersons);
    }

    //=========== Command History=============================================================
    @Override
    public ReadOnlyCommandHistory getCommandHistory() {
        return commandHistory;
    }

    @Override
    public void addToCommandHistory(String validCommandInput) {
        commandHistory.addToCommandHistory(validCommandInput);
    }

    @Override
    public String nextCommand() {
        return commandHistory.getNextCommand();
    }

    @Override
    public String prevCommand() {
        return commandHistory.getPrevCommand();
    }

    //=========== equals method =============================================================
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
                && filteredPersons.equals(other.filteredPersons);
    }
}
