package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.SortCommand.SortBy;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENT = unused -> true;
    Predicate<Tutor> PREDICATE_SHOW_ALL_TUTOR = unused -> true;
    Predicate<TuitionClass> PREDICATE_SHOW_ALL_TUITIONCLASS = unused -> true;

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
     * Returns the user prefs' tutor address book file path.
     */
    Path getTutorAddressBookFilePath();

    /**
     * Sets the user prefs' tutor address book file path.
     */
    void setTutorAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the user prefs' student address book file path.
     */
    Path getStudentAddressBookFilePath();

    /**
     * Sets the user prefs' student address book file path.
     */
    void setStudentAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the user prefs' tuition class address book file path.
     */
    Path getTuitionClassAddressBookFilePath();

    /**
     * Sets the user prefs' tuition class address book file path.
     */
    void setTuitionClassAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Exports the address books to csv
     */
    void export();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    void setStudents(List<Student> persons);

    void setTutors(List<Tutor> tutors);

    void setTuitionClasses(List<TuitionClass> tuitionClasses);

    /**
     * Deletes the given tuition class.
     * The tuition class must exist in the address book.
     */
    void deleteTuitionClass(TuitionClass target);

    /**
     * Returns true if a class with the same name as {@code tuitionClass} exists in the database.
     */
    boolean hasTuitionClass(TuitionClass tuitionClass);

    /**
     * Adds the given class.
     * {@code tuitionClass} must not already exist in the database.
     */
    void addTuitionClass(TuitionClass tuitionClass);

    /**
     * Returns the tuition class from the tuition class list if the tuition class name
     * matches the specified {@code name}.
     * @return the tuition class that has the same name as the specified {@code name}.
     */
    TuitionClass getTuitionClass(Name name);

    /**
     * Replaces the given class {@code target} with {@code editedClass}.
     * {@code target} must exist in the database.
     * The name of {@code editedClass} must not be the same as another existing class in the database.
     */
    void setTuitionClass(TuitionClass target, TuitionClass editedClass);

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

    void sortList(ListType type, SortBy method);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Tutor> getFilteredTutorList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorList(Predicate<Tutor> predicate);

    /**
     * Returns an unmodifiable view of the filtered class list
     */
    ObservableList<TuitionClass> getFilteredTuitionClassList();

    /**
     * Updates the filter of the filtered class list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTuitionClassList(Predicate<TuitionClass> predicate);

    /**
     * Updates the type of the current list
     **/
    void updateCurrentListType(ListType type);

    /**
     * Returns the type of the current list
     **/
    ListType getCurrentListType();

    /**
     * Returns the current list
     **/
    FilteredList<?> getCurrentList();

    /**
     * The type of the current list.
     **/
    enum ListType { STUDENT_LIST, TUTOR_LIST, TUITIONCLASS_LIST }
}
