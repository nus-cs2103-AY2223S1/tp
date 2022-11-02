package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.DistinctExamList;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.DistinctModuleList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.DistinctTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final DistinctModuleList modules;
    private final DistinctTaskList tasks;
    private final DistinctExamList exams;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new DistinctModuleList();
        tasks = new DistinctTaskList();
        exams = new DistinctExamList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setTasks(newData.getTaskList());
        setModules(newData.getModuleList());
        setExams(newData.getExamList());
    }


    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void addModule(Module mod) {
        modules.addModule(mod);
    }

    //// task-level operations

    /**
     * Returns true if a task with the same module and description as {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    public boolean hasTaskWithModule(Module module) {
        return tasks.containsModule(module);
    }

    /**
     * Adds a task to the task list.
     * The task must not already exist in the task list.
     */
    public void addTask(Task task) {
        tasks.addTask(task);
        modules.updateTotalNumOfTasks(task.getModule(), tasks);
        modules.updateNumOfCompletedTasks(task.getModule(), tasks);
        if (task.isLinked()) {
            exams.updateTotalNumOfTasks(task.getExam(), tasks);
            exams.updateNumOfCompletedTasks(task.getExam(), tasks);
        }
    }


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
    public void replaceTask(Task target, Task editedTask, boolean isSameTask) throws DuplicateTaskException {
        requireAllNonNull(target, editedTask, isSameTask);
        tasks.replaceTask(target, editedTask, isSameTask);
        modules.updateNumOfCompletedTasks(target.getModule(), tasks);
        modules.updateTotalNumOfTasks(target.getModule(), tasks);
        modules.updateNumOfCompletedTasks(editedTask.getModule(), tasks);
        modules.updateTotalNumOfTasks(editedTask.getModule(), tasks);

        if (target.isLinked() && !editedTask.isLinked()) {
            // to update exam when a task is unlinked from an exam
            exams.updateTotalNumOfTasks(target.getExam(), tasks);
            exams.updateNumOfCompletedTasks(target.getExam(), tasks);
        }

        if (editedTask.isLinked()) {
            // to update exam for linked tasks
            exams.updateTotalNumOfTasks(editedTask.getExam(), tasks);
            exams.updateNumOfCompletedTasks(editedTask.getExam(), tasks);
        }
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
        modules.updateNumOfCompletedTasks(key.getModule(), tasks);
        modules.updateTotalNumOfTasks(key.getModule(), tasks);
        if (key.isLinked()) {
            exams.updateNumOfCompletedTasks(key.getExam(), tasks);
            exams.updateTotalNumOfTasks(key.getExam(), tasks);
        }
    }

    /**
     * Resets number of tasks and number of completed tasks of all modules and exams to 0.
     */
    public void resetAllTaskCount() {
        modules.resetAllTaskCount();
        exams.resetAllTaskCount();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + "\n" + modules.getUnmodifiableModuleList().size() + " modules"
                + "\n" + tasks.getUnmodifiableTaskList().size() + "tasks"
                + "\n" + exams.getUnmodifiableExamList().size() + "exams";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Checks whether the module list contains the module.
     *
     * @param module The module that is being checked.
     * @return true if the module list contains the module; else returns false.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.containsModule(module);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     * {@code key} must not be tied to any tasks in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.getUnmodifiableModuleList();
    }

    /**
     * Replaces the given Module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module list.
     *
     * @throws DuplicateModuleException if module identity of {@code editedModule} is the same as another module
     *     in the list (other than {@code target}).
     */
    public void replaceModule(Module target, Module editedModule) throws DuplicateModuleException {
        requireAllNonNull(target, editedModule);

        modules.replaceModule(target, editedModule);
    }

    /**
     * Sorts the task list in the address book.
     *
     * @param criteria The criteria used for sorting the task list.
     */
    public void sortTaskList(Criteria criteria) {
        requireNonNull(criteria);
        tasks.sortTasks(criteria);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.getUnmodifiableTaskList();
    }

    /**
     * Returns true if an exam with the same module and exam description and exam date
     * as {@code exam} exists in the exam list.
     */
    public boolean hasExam(Exam exam) {
        requireNonNull(exam);
        return exams.contains(exam);
    }

    public boolean hasExamWithModule(Module module) {
        return exams.containsModule(module);
    }

    /**
     * Adds an exam to the exam list.
     * The exam must not already exist in the exam list.
     */
    public void addExam(Exam exam) {
        exams.addExam(exam);
    }


    /**
     * Replaces the given exam {@code target} with {@code editedExam}.
     * {@code target} must exist in the exam list.
     *
     * @throws DuplicateExamException if task identity of {@code editedExam} is the same as another exam
     *     in the list (other than {@code target}).
     */
    public void replaceExam(Exam target, Exam editedExam, boolean isSameExam) throws DuplicateExamException {
        requireAllNonNull(target, editedExam);
        exams.replaceExam(target, editedExam, isSameExam);
    }

    public void setExams(List<Exam> exams) {
        this.exams.setExams(exams);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeExam(Exam key) {
        exams.remove(key);
    }

    //// util methods
    @Override
    public ObservableList<Exam> getExamList() {
        return exams.getUnmodifiableExamList();
    }

    /**
     * Unlinks all tasks that are currently linked to {@code exam}.
     * @param exam The exam to unlink all tasks from.
     */
    public void unlinkTasksFromExam(Exam exam) {
        tasks.unlinkTasksFromExam(exam);
    }

    /**
     * Replaces task by changing its given exam field from {@code previousExam}
     * to {@code newExam} for tasks that have their exam field as {@code previousExam}.
     * @param previousExam The exam in the task's exam field.
     * @param newExam The new exam which will replace the previous exam in the task's exam field.
     */
    public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
        requireAllNonNull(previousExam, newExam);
        tasks.updateExamFieldForTask(previousExam, newExam);
    }

    /**
     * Returns true if {@code examToEdit} is linked to any task, otherwise false.
     */
    public boolean isExamLinkedToTask(Exam examToEdit) {
        requireNonNull(examToEdit);
        return tasks.isExamLinkedToTask(examToEdit);
    }
    /**
     * Replaces task by changing its given module field from {@code previousModule}
     * to {@code newModule} for tasks that have their module field as {@code previousModule}.
     * @param previousModule The module in the task's module field.
     * @param newModule The new module which will replace the previous module in the task's module field.
     */
    public void updateModuleFieldForTask(Module previousModule, Module newModule) {
        requireAllNonNull(previousModule, newModule);
        tasks.updateModuleFieldForTask(previousModule, newModule);
    }

    /**
     * Replaces exam by changing its given module field from {@code previousModule}
     * to {@code newModule} for exams that have their module field as {@code previousModule}.
     * @param previousModule The module in the exam's module field.
     * @param newModule The new module which will replace the previous module in the exam's module field.
     */
    public void updateModuleFieldForExam(Module previousModule, Module newModule) {
        requireAllNonNull(previousModule, newModule);
        exams.updateModuleFieldForExam(previousModule, newModule);
    }

    /**
     * Deletes tasks that have their module field as {@code module}.
     * @param module The module in the task's module field.
     */
    public void deleteTasksWithModule(Module module) {
        requireNonNull(module);
        tasks.deleteTasksWithModule(module);
    }

    /**
     * Deletes exams that have their module field as {@code module}.
     * @param module The module in the exam's module field.
     */
    public void deleteExamsWithModule(Module module) {
        requireNonNull(module);
        exams.deleteExamsWithModule(module);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tasks.equals(((AddressBook) other).tasks)
                && exams.equals(((AddressBook) other).exams)
                && modules.equals(((AddressBook) other).modules));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
