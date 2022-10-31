package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;

/**
 * Represents the in-memory model of InterNUS data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final SortedList<Person> filteredPersons;
    private final SortedList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        // Solution below adapted from
        // https://stackoverflow.com/questions/17958337/javafx-tableview-with-filteredlist-jdk-8-does-not-sort-by-column
        filteredPersons = new SortedList<>(new FilteredList<>(this.addressBook.getPersonList()));
        filteredInternships = new SortedList<>(new FilteredList<>(this.addressBook.getInternshipList()));
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
        // Since we are just swapping between 2 observable lists and they are wrappers around
        // the source list, it is safe to swap between SortedList and FilteredList.
        @SuppressWarnings("unchecked")
        FilteredList<Person> personList = (FilteredList<Person>) filteredPersons.getSource();
        personList.setPredicate(predicate);
    }

    @Override
    public void refreshPersonList() {
        FilteredList<Person> personList = (FilteredList<Person>) filteredPersons.getSource();
        Predicate<? super Person> predicate = personList.getPredicate();
        personList.setPredicate(PREDICATE_SHOW_NO_PERSONS);
        personList.setPredicate(predicate);
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return addressBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        addressBook.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        addressBook.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        addressBook.setInternship(target, editedInternship);
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        // Since we are just swapping between 2 observable lists and they are wrappers around
        // the source list, it is safe to swap between SortedList and FilteredList.
        @SuppressWarnings("unchecked")
        FilteredList<Internship> internshipList = (FilteredList<Internship>) filteredInternships.getSource();
        internshipList.setPredicate(predicate);
    }

    @Override
    public void refreshInternshipList() {
        FilteredList<Internship> internshipList = (FilteredList<Internship>) filteredInternships.getSource();
        Predicate<? super Internship> predicate = internshipList.getPredicate();
        internshipList.setPredicate(PREDICATE_SHOW_NO_INTERNSHIPS);
        internshipList.setPredicate(predicate);
    }

    @Override
    public void sortPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        filteredPersons.setComparator(comparator);
    }

    @Override
    public void sortInternshipList(Comparator<Internship> comparator) {
        requireNonNull(comparator);
        filteredInternships.setComparator(comparator);
    }

    @Override
    public int getNextPersonId() {
        return addressBook.getNextPersonId();
    }

    @Override
    public int getNextInternshipId() {
        return addressBook.getNextInternshipId();
    }

    @Override
    public Person findPersonById(PersonId personId) {
        return addressBook.findPersonById(personId);
    }

    @Override
    public Internship findInternshipById(InternshipId internshipId) {
        return addressBook.findInternshipById(internshipId);
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
                && filteredInternships.equals(other.filteredInternships);
    }

}
