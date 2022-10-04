package jarvis.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import jarvis.commons.core.GuiSettings;
import jarvis.model.student.Student;
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
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Replaces student book data with the data in {@code addressBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the StudentBook */
    ReadOnlyStudentBook getStudentBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
