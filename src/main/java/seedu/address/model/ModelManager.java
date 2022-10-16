package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final Set<Predicate<Person>> namePredicates;
    private final Set<Predicate<Person>> tagPredicates;
    private final FilteredList<Person> filteredPersons;
    private final TargetPerson targetPerson;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.namePredicates = new HashSet<>();
        this.tagPredicates = new HashSet<>();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        targetPerson = new TargetPerson();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs ======================================================================

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

    // =========== AddressBook ====================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        clearTargetPerson();
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

        if (isTargetPerson(target)) {
            clearTargetPerson();
        }
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        clearFiltersInFilteredPersonList();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);

        if (isTargetPerson(target)) {
            setTargetPerson(editedPerson);
        }
    }

    // =========== Filtered Person List ===========================================================
    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public void addTag(Tag tag) {
        requireNonNull(tag);
        addressBook.createTag(tag);
    }

    @Override
    public void removeTags(Person target, Collection<Tag> tags) {
        Person untaggedPerson = addressBook.removeTags(target, tags);
        clearFiltersInFilteredPersonList();

        if (isTargetPerson(target)) {
            setTargetPerson(untaggedPerson);
        }
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void addNewFilterToFilteredPersonList(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        predicate.addNameFiltersToSet(namePredicates);
        predicate.addTagFiltersToSet(tagPredicates);
        updateFilteredPersonList();
    }

    @Override
    public void clearFiltersInFilteredPersonList() {
        namePredicates.clear();
        tagPredicates.clear();
        updateFilteredPersonList();
    }

    @Override
    public void removeFilterFromFilteredPersonList(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        predicate.removeNameFiltersFromSet(namePredicates);
        predicate.removeTagFiltersFromSet(tagPredicates);
        updateFilteredPersonList();
    }

    private void updateFilteredPersonList() {
        Predicate<Person> namePredicate = namePredicates.size() == 0 ? PREDICATE_SHOW_ALL_PERSONS
                : namePredicates.stream()
                        .reduce(notused -> false, (pred1, pred2) -> pred1.or(pred2));
        Predicate<Person> tagPredicate = tagPredicates.size() == 0 ? PREDICATE_SHOW_ALL_PERSONS
                : tagPredicates.stream()
                        .reduce(notused -> false, (pred1, pred2) -> pred1.or(pred2));
        filteredPersons.setPredicate(namePredicate.and(tagPredicate));
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
        return addressBook.equals(other.addressBook) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    // =========== Target Person Accessors =============================================================
    @Override
    public ObservableList<Person> getTargetPersonAsObservableList() {
        return targetPerson.getAsObservableList();
    }

    @Override
    public void setTargetPerson(Person person) {
        targetPerson.set(person);
    }

    @Override
    public void clearTargetPerson() {
        targetPerson.clear();
    }

    @Override
    public boolean isTargetPerson(Person person) {
        return targetPerson.isSamePerson(person);
    }

    @Override
    public boolean hasTargetPerson() {
        return targetPerson.isPresent();
    }

    @Override
    public Person getTargetPerson() {
        return targetPerson.get();
    }
}
