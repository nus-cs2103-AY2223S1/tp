package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<TutorialGroup> filteredTutorialGroups;
    private final ObservableMap<GradeKey, Grade> grades;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredTutorialGroups = new FilteredList<>(this.addressBook.getTutorialGroupList());
        grades = FXCollections.observableMap(this.addressBook.getGradeMap());
        Task.setGradesMap(grades);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
        addressBook.deleteStudentInTask(this, target);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Student findStudent(String studentName) {
        return addressBook.findStudent(studentName);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        addressBook.setStudent(target, editedStudent);
        addressBook.editStudentInTask(this, target, editedStudent);
    }

    @Override
    public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        return addressBook.hasTutorialGroup(tutorialGroup);
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup target) {
        addressBook.removeTutorialGroup(target);
    }

    @Override
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        addressBook.addTutorialGroup(tutorialGroup);
        updateFilteredTutorialGroupList(PREDICATE_SHOW_ALL_TUTORIAL_GROUPS);
    }


    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
        return filteredTutorialGroups;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredStudentListByTg(TutorialGroup tutorialGroup) {
        Predicate<Student> inSameTutorialGrp = s -> {
            if (s.getTutorialGroup() != null) {
                return s.getTutorialGroup().equals(tutorialGroup);
            } else {
                return false;
            }
        };
        requireNonNull(inSameTutorialGrp);
        filteredStudents.setPredicate(inSameTutorialGrp);
    }

    @Override
    public void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate) {
        requireNonNull(predicate);
        filteredTutorialGroups.setPredicate(predicate);
    }



    //=========== Task ================================================================================

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        addressBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Grade Accessors =============================================================

    /**
     * Returns an unmodifiable view of the map of {@code (GradeKey, Grade)} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableMap<GradeKey, Grade> getGradeMap() {
        return grades;
    }

    //=========== Grade ================================================================================

    @Override
    public void addGrade(GradeKey gradeKey, Grade grade) {
        addressBook.addGrade(gradeKey, grade);
        //TODO: Show grade somehow
    }

    @Override
    public void updateGrades(Task taskToEdit, Task editedTask) {
        addressBook.updateGrades(taskToEdit, editedTask);
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
                && filteredStudents.equals(other.filteredStudents);
    }

}
