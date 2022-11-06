package seedu.clinkedin.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.clinkedin.commons.core.GuiSettings;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.tag.TagType;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' clinkedin book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' clinkedin book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces clinkedin book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Saves the current address book state for undo/redo. */
    void commitAddressBook();

    /** Restores the address book to its previous state. */
    void undoAddressBook();

    /** Restores the address book to its previously undone state. */
    void redoAddressBook();

    /** Returns true if the model has previous address states to restore. */
    boolean canUndoAddressBook();

    /** Returns true if the model has undone address states to restore. */
    boolean canRedoAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the clinkedin book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    void addPersonWithoutCommitting(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns a summary of the statistics of the persons in the address book.
     */
    DoubleSummaryStatistics getStats();

    HashMap<String, Integer> getRatingCount();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the total number of persons in the address book.
     */
    int getTotalNumberOfPersons();

    /**
     * Returns the total number of filtered persons in the address book.
     */
    int getFilteredNumberOfPersons();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    ///**
    // * Returns an unmodifiable view of the sorted persons list.
    // */
    // ObservableList<Person> getSortedPersonList();

    /**
     * Updates the comparator of the sorted person list to sort by the given
     * {@code comparator}
     * @param comparator comparator to update sorted persons list with.
     */
    void updateSort(Comparator<Person> comparator);

    void deleteTagTypeForAllPerson(TagType toDelete);

    void editTagTypeForAllPerson(TagType toEdit, TagType editTo);

    void setPrefixMap(Map<Prefix, TagType> prefixMap);
}
