package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Exam> PREDICATE_SHOW_ALL_EXAMS = unused -> true;


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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

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

    void addModule(Module module);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a task with the same description and module as {@code task} exists in the task list.
     */
    boolean hasTask(Task task);

    /**
     * Returns true if a task with {@code module} exists in the task list.
     */
    boolean hasTaskWithModule(Module module);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task list.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     * If {@code isSameTask} is true, the task identity of {@code editedTask} should be the same as {@code target}.
     *
     * @param target the task to be replaced.
     * @param editedTask the edited task to replace {@code target}.
     * @param isSameTask true if {@code target} has the same task identity as {@code editedTask}, false otherwise.
     * @throws DuplicateTaskException if {@code isSameTask} is false but task identity of {@code editedTask}
     *     is the same as another task in the list (other than {@code target}).
     */
    void replaceTask(Task target, Task editedTask, boolean isSameTask) throws DuplicateTaskException;

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    boolean hasModule(Module module);
    void updateFilteredModuleList(Predicate<Module>predicate);
    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Deletes the given module.
     * The module must exist in the address book.
     */
    void deleteModule(Module target);

    void replaceModule(Module target, Module editedModule);


    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);


    void sortTaskList(Criteria criteria);

    /**
     * Updates the task list to unlink all tasks that are currently linked to the give {@code exam}.
     * @param exam
     */
    void unlinkTasksFromExam(Exam exam);

    /**
     * Returns true if an exam with the same description and module and exam date
     * as {@code exam} exists in the exam list.
     */
    boolean hasExam(Exam exam);

    void deleteExam(Exam target);

    boolean hasExamWithModule(Module module);

    /**
     * Adds the given exam.
     * {@code exam} must not already exist in the exam list.
     */
    void addExam(Exam exam);


    /**
     * Replaces the given exam {@code target} with {@code editedExam}.
     * {@code target} must exist in the exam list.
     *
     * @throws DuplicateExamException if task identity of {@code editedExam} is the same as another exam
     *     in the list (other than {@code target}).
     */
    void replaceExam(Exam target, Exam editedExam, boolean isSameExam) throws DuplicateExamException;

    /** Returns an unmodifiable view of the filtered exam list */
    ObservableList<Exam> getFilteredExamList();

    /**
     * Updates the filter of the filtered exam list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExamList(Predicate<Exam>predicate);


    /**
     * Updates the exam field in task by replacing the previous exam with the new exam.
     * @param previousExam The exam in the task's exam field.
     * @param newExam The new exam which will replace the previous exam in the task's exam field.
     */
    void updateExamFieldForTask(Exam previousExam, Exam newExam);

    /**
     * Returns true if {@code examToEdit} is linked to any task, otherwise false.
     */
    boolean isExamLinkedToTask(Exam examToEdit);

    /**
     * Updates the module field in task by replacing the previous module with the new module.
     * @param previousModule The module in the task's module field.
     * @param newModule The new module which will replace the previous module in the task's module field.
     */
    void updateModuleFieldForTask(Module previousModule, Module newModule);

    /**
     * Updates the module field in exam by replacing the previous module with the new module.
     * @param previousModule The module in the exam's module field.
     * @param newModule The new module which will replace the previous module in the exam's module field.
     */
    void updateModuleFieldForExam(Module previousModule, Module newModule);

    /**
     * Deletes tasks that have their module field as {@code module}.
     * @param module The module in the task's module field.
     */
    void deleteTasksWithModule(Module module);

    /**
     * Deletes exams that have their module field as {@code module}.
     * @param module The module in the exam's module field.
     */
    void deleteExamsWithModule(Module module);

}
