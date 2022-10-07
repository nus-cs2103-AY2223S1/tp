package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
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

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

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
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Sorts the address book by name in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    void sortByName(Boolean isReverse);

    /**
     * Sorts the address book by phone number in increasing order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    void sortByPhone(Boolean isReverse);

    /**
     * Sorts the address book by email in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    void sortByEmail(Boolean isReverse);

    /**
     * Sorts the address book by address in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    void sortByAddress(Boolean isReverse);

    /**
     * Sorts the address book by a tag.
     * Contacts with the tag appear before those without the tag.
     *
     * @param tag       The tag to sort with
     * @param isReverse Whether the sorting should be in reverse order
     */
    void sortByTag(Tag tag, Boolean isReverse);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     *  Returns true if tag exists.
     * @param tag tag added.
     * @return True if tag exists. False if otherwise.
     */
    boolean hasTag(Tag tag);

    void addTag(Tag tag);

    void editTag(Tag oldTag, Tag newTag);

    ObservableList<Tag> getTagList();
}
