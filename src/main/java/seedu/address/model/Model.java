package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.Class;
import seedu.address.model.student.Person;
import seedu.address.model.timeRange.TimeRange;

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
    Path getTeachersPetFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setTeachersPetFilePath(Path teachersPetFilePath);

    /**
     * Replaces teacher's pet data with the data in {@code teachersPet}.
     */
    void setTeachersPet(ReadOnlyTeachersPet teachersPet);

    /** Returns the TeachersPet */
    ReadOnlyTeachersPet getTeachersPet();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given student {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedPerson} must not be the same as another existing student in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns the first available class within the range specified by {@code TimeRange}
     * @return the first available {@code Class}
     */
    Class findAvailableClass(TimeRange timeRange);

    /**
     * Sorts the current filtered student list with the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortPersons(Comparator<Person> comparator);
    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the schedule list for that day*/
    ObservableList<Person> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Person> predicate);

    /**
     * Updates previous state of the address book.
     */
    void updateTeachersPetHistory();

    /**
     * Undo last change made to state of teacher's pet.
     */
    void undo() throws CommandException;

    /**
     * Deletes the latest addition in the ArrayList of Teachers Pet.
     */
    void deleteTeachersPetHistory();

}
