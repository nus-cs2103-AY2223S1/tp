package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.message.Message;
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
    private final Set<Predicate<Person>> personPredicates;
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
        this.personPredicates = new HashSet<>();
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

    // =========== Tags ===========================================================

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

    // =========== Message Templates ===========================================================

    @Override
    public void addMessage(Message message) {
        requireNonNull(message);
        addressBook.createMessage(message);
    }

    @Override
    public void deleteMessage(Message message) {
        requireNonNull(message);
        addressBook.deleteMessage(message);
    }

    @Override
    public boolean hasMessage(Message message) {
        requireNonNull(message);
        return addressBook.hasMessage(message);
    }

    @Override
    public List<Message> getMessages() {
        return addressBook.getMessageTemplates();
    }

    // =========== Filtered Person List ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void addNewFilterToFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        personPredicates.add(predicate);
        updateFilteredPersonList();
    }

    @Override
    public void clearFiltersInFilteredPersonList() {
        personPredicates.clear();
        updateFilteredPersonList();
    }

    @Override
    public void removeFilterFromFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        personPredicates.remove(predicate);
        updateFilteredPersonList();
    }

    private void updateFilteredPersonList() {
        Predicate<Person> predicate = personPredicates.size() == 0 ? PREDICATE_SHOW_ALL_PERSONS
                : personPredicates.stream()
                        .reduce(notused -> false, (pred1, pred2) -> pred1.or(pred2));
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
