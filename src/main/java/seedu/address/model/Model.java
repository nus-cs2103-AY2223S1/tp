package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
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
     * Returns the user prefs' FinBook file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' FinBook file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces FinBook data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the FinBook.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the FinBook.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the FinBook.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the FinBook.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the FinBook.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a password has been set for FinBook.
     */
    boolean isPasswordSet();

    /**
     * Returns true if password is correct.
     */
    boolean isPasswordCorrect(String password);

    /**
     * Updates password if old password provided is correct.
     */
    void updatePassword(String oldPassword, String newPassword);

    /**
     * Sorts clients according to params.
     */
    void sort(String sortParam);
}
