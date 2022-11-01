package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private CurrentlyViewedPerson currentlyViewedPerson;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        resetCurrentlyViewedPerson();
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
        updateFilteredPersonList(x -> true);
        resetCurrentlyViewedPerson();
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
        if (target.equals(currentlyViewedPerson.getPerson()) && filteredPersons.size() > 0) {
            currentlyViewedPerson = generateFirstPerson();
        }
        if (filteredPersons.size() <= 0) {
            currentlyViewedPerson = new CurrentlyViewedPerson(null, null);
        }
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int intIndex = filteredPersons.indexOf(person);
        if (intIndex > -1) {
            Index index = Index.fromZeroBased(filteredPersons.indexOf(person));
            currentlyViewedPerson = new CurrentlyViewedPerson(person, index);
        }
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
        int intIndex = filteredPersons.indexOf(editedPerson);
        if (intIndex > -1) {
            Index index = Index.fromZeroBased(filteredPersons.indexOf(editedPerson));
            currentlyViewedPerson = new CurrentlyViewedPerson(editedPerson, index);
        }
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

    //=========== Currently Viewed Person Accessors =============================================================
    @Override
    public Person getCurrentlyViewedPerson() {
        return currentlyViewedPerson.getPerson();
    }

    @Override
    public Index getCurrentlyViewedIndex() {
        return currentlyViewedPerson.getIndex();
    }

    @Override
    public void updateCurrentlyViewedPerson(Person person, Index index) {
        requireAllNonNull(person, index);
        currentlyViewedPerson = new CurrentlyViewedPerson(person, index);
    }

    private void resetCurrentlyViewedPerson() {
        if (filteredPersons.size() > 0) {
            currentlyViewedPerson = generateFirstPerson();
        } else {
            currentlyViewedPerson = new CurrentlyViewedPerson(null, null);
        }
    }

    private CurrentlyViewedPerson generateFirstPerson() {
        Index index = Index.fromOneBased(1);
        return new CurrentlyViewedPerson(filteredPersons.get(index.getZeroBased()), index);
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

    @Override
    public int getPersonCount() {
        return addressBook.getPersonList().size();
    }

    @Override
    public String getCensus() {
        return addressBook.getCensus(this);
    }

    /**
     * Class to represent the currently selected person Object.
     */
    public static class CurrentlyViewedPerson {
        private final Person person;
        private final Index index;

        /**
         * Creates a Currently Viewed Person Object.
         * @param person Person viewed.
         * @param index The Index in the filteredPersonList.
         */
        public CurrentlyViewedPerson(Person person, Index index) {
            this.person = person;
            this.index = index;
        }

        public Person getPerson() {
            return person;
        }

        public Index getIndex() {
            return index;
        }
    }
}
