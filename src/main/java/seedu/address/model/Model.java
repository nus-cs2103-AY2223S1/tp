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
     * Returns the user prefs' mass linkers file path.
     */
    Path getMassLinkersFilePath();

    /**
     * Sets the user prefs' mass linkers file path.
     */
    void setMassLinkersFilePath(Path massLinkersFilePath);

    /**
     * Replaces mass linkers data with the data in {@code massLinkers}.
     */
    void setMassLinkers(ReadOnlyMassLinkers massLinkers);

    /** Returns the MassLinkers */
    ReadOnlyMassLinkers getMassLinkers();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the mass linkers.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the mass linkers.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the mass linkers.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the mass linkers.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the mass linkers.
     */
    void setPerson(Person target, Person editedPerson);

    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

}
