package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.User;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a user already exists in the address book.
     */
    boolean hasUser();

    /**
     * Adds the user to the address book.
     */
    void addUser(User user);

    /**
     * Returns the current user of the address book or EmptyUser is there is no user.
     */
    User getUser();

    /**
     * Deletes the current user from the address book and replaces it with an EmptyUser.
     */
    void deleteUser();

    /**
     * Replaces the current user with {@code editedUser}.
     */
    void setUser(User editedUser);

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

    void addLessonToUser(Lesson lesson) throws CommandException;

    void removeLessonToUser(Lesson lesson) throws CommandException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * @return the set of lessons in focus.
     */
    Set<Lesson> getTimetable();

    /**
     * Sets the timetable in focus.
     */
    boolean setTimetable(Set<Lesson> lessons);

    /**
     * Updates the User's and all Persons in contacts list by shifting all current mods into previous mods.
     */
    void nextSem() throws CommandException;

    /**
     * Commits current AddressBook into VersionedAddressBook.
     */
    void commitAddressBook();

    /**
     * Returns if VersionedAddressBook can be undid.
     * @return If undo is possible
     */
    boolean canUndoAddressBook();

    /**
     * Returns if VersionedAddressBook can be redid.
     * @return If redo is possible
     */
    boolean canRedoAddressBook();

    /**
     * Reverts the AddressBook to the previous state.
     */
    void undoAddressBook();

    /**
     * Reverts the AddressBook to the forward state.
     */
    void redoAddressBook();

}
