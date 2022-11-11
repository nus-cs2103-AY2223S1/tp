package coydir.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import coydir.commons.core.GuiSettings;
import coydir.model.person.Person;
import javafx.collections.ObservableList;

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
     * Returns the user prefs' database file path.
     */
    Path getDatabaseFilePath();

    /**
     * Sets the user prefs' database file path.
     */
    void setDatabaseFilePath(Path databaseFilePath);

    /**
     * Replaces database data with the data in {@code database}.
     */
    void setDatabase(ReadOnlyDatabase database);

    /** Returns the database */
    ReadOnlyDatabase getDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the database.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the database.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the database.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the database.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
