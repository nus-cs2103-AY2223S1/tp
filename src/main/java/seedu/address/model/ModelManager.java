package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ObservableList<Pair<Person, Reminder>> unsortedReminders;
    private final SortedList<Pair<Person, Reminder>> reminders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        this.unsortedReminders = convert(this.addressBook.getPersonList());
        this.reminders = new SortedList<>(unsortedReminders);
        this.reminders.setComparator(Comparator.comparing(x -> x.getKey().getName().fullName));
        this.reminders.setComparator(Comparator.comparing(x -> x.getValue().date));

        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
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
        this.unsortedReminders.setAll(convert(addressBook.getPersonList()));
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
        deletePersonReminders(target);
        addPersonReminders(editedPerson);
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

    //=========== Sorted Reminder List Accessors =============================================================

    /**
     * Converts the {@code ObservableList} into an {@code ObservableList} with a Pair of Person and Reminder
     * returns the new {@code ObservableList}
     * @param personList The {@code ObservableList} to be converted
     * @return The converted {@code ObservableList}
     */
    private ObservableList<Pair<Person, Reminder>> convert(ObservableList<Person> personList) {
        ArrayList<Pair<Person, Reminder>> total = new ArrayList<>();
        for (Person person : personList) {
            total.addAll(person.getReminders().stream().map(reminder -> new Pair<Person, Reminder>(
                    person, reminder
            )).collect(Collectors.toList()));
        }
        return FXCollections.observableList(total);
    }

    /**
     * Returns the sorted list of {@code Reminders}
     */
    @Override
    public SortedList<Pair<Person, Reminder>> getSortedReminderList() {
        return reminders;
    }

    @Override
    public void addReminder(Person person, Reminder reminder) {
        unsortedReminders.add(new Pair<>(person, reminder));
    }

    @Override
    public void deleteReminder(Pair<Person, Reminder> target) {
        unsortedReminders.remove(target);
        target.getKey().deleteReminder(target.getValue());
    }

    @Override
    public void deletePersonReminders(Person personToDelete) {
        unsortedReminders.removeIf(pair -> pair.getKey().equals(personToDelete));
    }

    private void addPersonReminders(Person personToAdd) {
        Reminder toRemove = null;
        Reminder toAdd = null;
        Iterator<Reminder> i = personToAdd.getReminders().iterator();
        while (i.hasNext()) {
            Reminder r = i.next();
            if (r.task.contains("Happy Birthday")) {
                toRemove = r;
                LocalDate birthday = personToAdd.getBirthday().value.withYear(LocalDate.now().getYear());
                if (birthday.isBefore(LocalDate.now())) {
                    birthday = birthday.plusYears(1);
                }
                r = new Reminder(Messages.generateHappyBirthdayMessage(personToAdd.getName()),
                        birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)));
                toAdd = r;
            }
            unsortedReminders.add(new Pair<>(personToAdd, r));
        }
        if (toRemove != null) {
            personToAdd.deleteReminder(toRemove);
            personToAdd.getReminders().add(toAdd);
        }
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
                && filteredPersons.equals(other.filteredPersons);
    }

}
