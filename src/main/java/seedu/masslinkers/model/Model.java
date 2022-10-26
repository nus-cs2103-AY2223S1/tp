package seedu.masslinkers.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.model.student.Student;

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
     * Returns true if a student with the same identity as {@code student} exists in the mass linkers.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the mass linkers.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the mass linkers.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the mass linkers.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the mass linkers.
     */
    void setStudent(Student target, Student editedStudent);

    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

}
