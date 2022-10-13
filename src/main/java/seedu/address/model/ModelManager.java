package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Tutor> filteredTutors;
    private final FilteredList<TuitionClass> filteredTuitionClass;
    /**
     * the type of the current list
     **/
    private ListType type;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        this.filteredTutors = new FilteredList<>(this.addressBook.getTutorList());
        this.filteredTuitionClass = new FilteredList<>(this.addressBook.getTuitionClassList());
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.type = ListType.STUDENT_LIST;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTutorAddressBookFilePath() {
        return userPrefs.getTutorAddressBookFilePath();
    }

    @Override
    public void setTutorAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setTutorAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getStudentAddressBookFilePath() {
        return userPrefs.getStudentAddressBookFilePath();
    }

    @Override
    public void setStudentAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setStudentAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getTuitionClassAddressBookFilePath() {
        return userPrefs.getTuitionClassAddressBookFilePath();
    }

    @Override
    public void setTuitionClassAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setTuitionClassAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setStudents(List<Student> persons) {
        this.addressBook.setStudents(persons);
    }

    @Override
    public void setTutors(List<Tutor> tutors) {
        this.addressBook.setTutors(tutors);
    }

    @Override
    public void setTuitionClasses(List<TuitionClass> tuitionClasses) {
        this.addressBook.setTuitionClasses(tuitionClasses);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTuitionClass(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return addressBook.hasTuitionClass(tuitionClass);
    }

    @Override
    public void deleteTuitionClass(TuitionClass target) {
        addressBook.removeTuitionClass(target);
    }

    @Override
    public void addTuitionClass(TuitionClass tuitionClass) {
        addressBook.addTuitionClass(tuitionClass);
    }

    @Override
    public void setTuitionClass(TuitionClass target, TuitionClass editedTuitionClass) {
        requireAllNonNull(target, editedTuitionClass);

        addressBook.setTuitionClass(target, editedTuitionClass);
    }
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Tutor} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Tutor> getFilteredTutorList() {
        return filteredTutors;
    }

    @Override
    public void updateFilteredTutorList(Predicate<Tutor> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code TuitionClass} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<TuitionClass> getFilteredTuitionClassList() {
        return filteredTuitionClass;
    }

    @Override
    public void updateFilteredTuitionClassList(Predicate<TuitionClass> predicate) {
        requireNonNull(predicate);
        filteredTuitionClass.setPredicate(predicate);
    }

    //=========== List Type Accessors =============================================================

    @Override
    public void updateCurrentListType(ListType type) {
        this.type = type;
    }

    @Override
    public ListType getCurrentListType() {
        return this.type;
    }

    @Override
    public FilteredList<?> getCurrentList() {
        switch (this.type) {
        case STUDENT_LIST:
            return filteredStudents;
        case TUTOR_LIST:
            return filteredTutors;
        case TUITIONCLASS_LIST:
            return filteredTuitionClass;
        default:
            return filteredStudents;
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTutors.equals(other.filteredTutors)
                && filteredTuitionClass.equals(other.filteredTuitionClass);
    }
}
