package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;



/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private final FilteredList<Module> moduleFilteredList;

    private final FilteredList<Task> taskFilteredList;

    private final FilteredList<Exam> examFilteredList;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        moduleFilteredList = new FilteredList<>(this.addressBook.getModuleList());
        taskFilteredList = new FilteredList<>(this.addressBook.getTaskList());
        examFilteredList = new FilteredList<>(this.addressBook.getExamList());
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

    //========== Task List ==================================================================================

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public boolean hasTaskWithModule(Module module) {
        requireNonNull(module);
        return addressBook.hasTaskWithModule(module);
    }


    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void replaceTask(Task target, Task editedTask, boolean isSameTask) {
        requireAllNonNull(target, editedTask, isSameTask);

        addressBook.replaceTask(target, editedTask, isSameTask);
    }

    @Override
    public void deleteTask(Task target) {
        addressBook.removeTask(target);
    }




    //========== Exam List ==================================================================================
    @Override
    public boolean hasExam(Exam exam) {
        requireNonNull(exam);
        return addressBook.hasExam(exam);
    }

    @Override
    public boolean hasExamWithModule(Module module) {
        requireNonNull(module);
        return addressBook.hasExamWithModule(module);
    }


    @Override
    public void addExam(Exam exam) {
        addressBook.addExam(exam);
        updateFilteredExamList(PREDICATE_SHOW_ALL_EXAMS);
    }

    @Override
    public void replaceExam(Exam target, Exam editedExam, boolean isSameExam) {
        requireAllNonNull(target, editedExam);
        addressBook.replaceExam(target, editedExam, isSameExam);
    }

    @Override
    public void deleteExam(Exam target) {
        addressBook.removeExam(target);
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


    //=============================Module Commands================================
    @Override
    public void addModule(Module module) {
        addressBook.addModule(module);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return addressBook.hasModule(module);
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return moduleFilteredList;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        moduleFilteredList.setPredicate(predicate);
    }

    @Override
    public void deleteModule(Module target) {
        addressBook.removeModule(target);
    }

    @Override
    public void replaceModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        addressBook.replaceModule(target, editedModule);
    }

    //================================Task Commands=====================================
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return taskFilteredList;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        taskFilteredList.setPredicate(predicate);
    }

    @Override
    public void sortTaskList(Criteria criteria) {
        requireNonNull(criteria);
        addressBook.sortTaskList(criteria);
    }

    @Override
    public void unlinkTasksFromExam(Exam exam) {
        requireNonNull(exam);
        addressBook.unlinkTasksFromExam(exam);
    }

    @Override
    public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
        requireAllNonNull(previousExam, newExam);
        addressBook.updateExamFieldForTask(previousExam, newExam);
    }

    @Override
    public void updateModuleFieldForTask(Module previousModule, Module newModule) {
        requireAllNonNull(previousModule, newModule);
        addressBook.updateModuleFieldForTask(previousModule, newModule);
    }

    @Override
    public void deleteTasksWithModule(Module module) {
        requireNonNull(module);
        addressBook.deleteTasksWithModule(module);
    }

    //================================Exam Commands=====================================
    @Override
    public ObservableList<Exam> getFilteredExamList() {
        return examFilteredList;
    }

    @Override
    public void updateFilteredExamList(Predicate<Exam> predicate) {
        requireNonNull(predicate);
        examFilteredList.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModelManager
                && this.addressBook.equals(((ModelManager) other).addressBook));
    }


    public boolean isExamLinkedToTask(Exam examToEdit) {
        requireNonNull(examToEdit);
        return addressBook.isExamLinkedToTask(examToEdit);
    }

    @Override
    public void updateModuleFieldForExam(Module previousModule, Module newModule) {
        requireAllNonNull(previousModule, newModule);
        addressBook.updateModuleFieldForExam(previousModule, newModule);
    }

    @Override
    public void deleteExamsWithModule(Module module) {
        requireNonNull(module);
        addressBook.deleteExamsWithModule(module);
    }

}
