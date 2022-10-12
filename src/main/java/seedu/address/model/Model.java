package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a tuition class with the same identity as {@code tuitionClass}
     * exists in the address book.
     */
    boolean hasTuitionClass(TuitionClass tuitionClass);

    /**
     * Deletes the given tuition class.
     * The tuition class must exist in the address book.
     */
    void deleteTuitionClass(TuitionClass target);

    /**
     * Adds the given tuition class.
     * {@code tuitionClass} must not already exist in the address book.
     */
    void addTuitionClass(TuitionClass tuitionClass);

    /**
     * Replaces the given tuition class {@code target} with {@code editedTuitionClass}.
     * {@code target} must exist in the address book.
     * The tuition class identity of {@code editedPerson} must not be the same as
     * another existing tuition class in the address book.
     */
    void setTuitionClass(TuitionClass target, TuitionClass editedTuitionClass);

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

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

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
     * the type of the current list
     **/
    enum ListType { STUDENT_LIST, TUTOR_LIST, TUITIONCLASS_LIST, PERSON_LIST }
}
