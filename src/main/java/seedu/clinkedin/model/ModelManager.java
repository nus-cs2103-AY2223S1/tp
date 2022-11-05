package seedu.clinkedin.model;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.clinkedin.commons.core.GuiSettings;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.commons.exceptions.CannotRedoAddressBookException;
import seedu.clinkedin.commons.exceptions.CannotUndoAddressBookException;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.TagType;

/**
 * Represents the in-memory model of the clinkedin book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    // private final SortedList<Person> sortedPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with CLInkedIn book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        // sortedPersons = new SortedList<>(filteredPersons);
    }

    public ModelManager() {
        this(new VersionedAddressBook(new AddressBook()), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

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

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        commitAddressBook();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void commitAddressBook() {
        addressBook.commit();
    }

    @Override
    public void undoAddressBook() {
        try {
            addressBook.undo();
        } catch (CannotUndoAddressBookException e) {
            logger.warning("No undoable state found.");
        }
    }

    @Override
    public void redoAddressBook() {
        try {
            addressBook.redo();
        } catch (CannotRedoAddressBookException e) {
            logger.warning("No redoable state found.");
        }
    }

    @Override
    public boolean canUndoAddressBook() {
        return addressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return addressBook.canRedo();
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        commitAddressBook();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        commitAddressBook();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        commitAddressBook();
    }

    @Override
    public int getTotalNumberOfPersons() {
        return addressBook.getCount();
    }

    /**
     * Returns a summary of the statistics of the persons' tags in the user's ClInkedIn using the
     * DoubleSummaryStatistics class.
     * @return a DoubleSummaryStatistics object containing the tag statistics of the persons in the user's ClInkedIn.
     */
    @Override
    public DoubleSummaryStatistics getStats() {
        DoubleSummaryStatistics stats = filteredPersons.stream().mapToDouble(Person::getTagCount).collect(
                DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine);
        logger.fine("Stats: " + stats);
        return stats;
    }

    @Override
    public HashMap<String, Integer> getRatingCount() {
        HashMap<String, Integer> ratingCount = new HashMap<>();
        for (Person person : filteredPersons) {
            assert person.getRating() != null : "Person's rating should not be null";
            String rating = person.getRating().toString() + "/10";
            if (ratingCount.containsKey(rating)) {
                ratingCount.put(rating, ratingCount.get(rating) + 1);
            } else {
                ratingCount.put(rating, 1);
            }
        }
        return ratingCount;
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
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

    // /**
    // * Returns an unmodifiable view of the list of {@code Person} backed by the
    // * internal list of
    // * {@code versionedAddressBook}
    // */
    // @Override
    // public ObservableList<Person> getSortedPersonList() {
    //    return sortedPersons;
    //}

    @Override
    public void updateSort(Comparator<Person> comparator) {
        requireNonNull(comparator);
        List<Person> sortedList = new ArrayList<>(addressBook.getPersonList());
        sortedList.sort(comparator);
        addressBook.setPersons(sortedList);
    }

    @Override
    public int getFilteredNumberOfPersons() {
        return filteredPersons.size();
    }

    @Override
    public void deleteTagTypeForAllPerson(TagType toDelete) {
        List<Person> personList = addressBook.getPersonList();
        personList.stream().forEach(x -> x.deleteTagType(toDelete));
        addressBook.setPersons(personList);
    }

    /**
     * Edits an existing tag type for all person.
     */
    public void editTagTypeForAllPerson(TagType toEdit, TagType editTo) {
        List<Person> personList = addressBook.getPersonList();
        List<Person> updatedPersonList = new ArrayList<>();
        for (Person p: personList) {
            UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
            if (p.getTags().keySet().contains(toEdit)) {
                tagTypeMap.setTagTypeMap(p.getTags());
                tagTypeMap.setTagType(toEdit, editTo);
                p.setTagTypeMap(tagTypeMap);
            }
            updatedPersonList.add(p);
        }
        addressBook.setPersons(updatedPersonList);
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
