package seedu.watson.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    public Predicate<Student> createPersonIsInClassPredicate(String studentClass);

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
     * Returns the user prefs' watson book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' watson book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the Database
     */
    ReadOnlyDatabase getDatabase();

    /**
     * Replaces watson book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyDatabase addressBook);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the watson book.
     */
    boolean hasPerson(Student student);

    Student getStudentByName(Name name);

    /**
     * Deletes the given student.
     * The student must exist in the watson book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the watson book.
     */
    void addPerson(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the watson book.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the watson book.
     */
    void setPerson(Student target, Student editedStudent);

    /**
     * Returns an unmodifiable view of the filtered student list
     */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    /**
     * Sorts the list of students by grade
     */
    void sortListByGrade(boolean isInAscending, String subjectName);
}
