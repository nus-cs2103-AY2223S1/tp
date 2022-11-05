package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

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
     * Returns the user prefs' Survin file path.
     */
    Path getSurvinFilePath();

    /**
     * Sets the user prefs' Survin file path.
     */
    void setSurvinFilePath(Path survinFilePath);

    /**
     * Replaces Survin data with the data in {@code survin}.
     */
    void setSurvin(ReadOnlySurvin survin);

    /** Returns Survin */
    ReadOnlySurvin getSurvin();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the Survin.
     */
    boolean hasPerson(Person person);

    Optional<Person> getPerson(Person person);

    /**
     * Deletes the given person. The person must exist in Survin.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person. {@code person} must not already exist in Survin
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in Survin. The person identity of
     * {@code editedPerson} must not be the same as another existing person in Survin.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Stores the current state of Survin.
     */
    void commitSurvin();

    /**
     * Undoes changes to Survin.
     */
    void undoSurvin();

    /**
     * Returns a boolean that indicates whether Survin can be undone.
     */
    boolean canUndoSurvin();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
