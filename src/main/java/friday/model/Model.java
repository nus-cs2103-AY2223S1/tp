package friday.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import friday.commons.core.GuiSettings;
import friday.model.student.Student;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' FRIDAY file path.
     */
    Path getFridayFilePath();

    /**
     * Sets the user prefs' FRIDAY file path.
     */
    void setFridayFilePath(Path fridayFilePath);

    /**
     * Replaces FRIDAY's data with the data in {@code friday}.
     */
    void setFriday(ReadOnlyFriday friday);

    /** Returns FRIDAY */
    ReadOnlyFriday getFriday();

    /**
     * Returns true if a person with the same identity as {@code person} exists in FRIDAY.
     */
    boolean hasPerson(Student student);

    /**
     * Deletes the given person.
     * The person must exist in FRIDAY.
     */
    void deletePerson(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in FRIDAY.
     */
    void addPerson(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in FRIDAY.
     * The person identity of {@code editedPerson} must not be the same as another existing person in FRIDAY.
     */
    void setPerson(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);
}
