package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.GradeMap;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.UniqueTutorialGroupList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final UniqueTaskList tasks;
    private final UniqueTutorialGroupList tutorialGroups;
    private final GradeMap grades;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tutorialGroups = new UniqueTutorialGroupList();
        tasks = new UniqueTaskList();
        grades = new GradeMap();
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
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Edit the student list in the task.
     */
    public void editStudentInTask(Model model, Student oldStudent, Student newStudent) {
        for (Task taskToEdit : tasks) {
            if (taskToEdit.getStudents() != null) {
                Set<Student> studentSet = taskToEdit.getStudents();
                Set<Student> newStudents = new HashSet<>();
                for (Student stud: studentSet) {
                    Student student;
                    if (Objects.equals(stud.getName(), oldStudent.getName())) {
                        student = newStudent;
                    } else {
                        student = stud;
                    }
                    newStudents.add(student);
                }
                Task editedTask = new Task(taskToEdit.getTaskName(), taskToEdit.getTaskDescription(),
                        taskToEdit.getTaskDeadline(), newStudents);
                model.setTask(taskToEdit, editedTask);
                model.updateGrades(taskToEdit, editedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
    }

    /**
     * Delete the student list in the task.
     */
    public void deleteStudentInTask(Model model, Student student) {
        for (Task taskToEdit : tasks) {
            if (taskToEdit.getStudents() != null) {
                Set<Student> studentSet = taskToEdit.getStudents();
                Set<Student> newStudents = new HashSet<>();
                for (Student stud: studentSet) {
                    if (!Objects.equals(stud.getName(), student.getName())) {
                        newStudents.add(stud);
                    }
                }
                Task editedTask = new Task(taskToEdit.getTaskName(), taskToEdit.getTaskDescription(),
                        taskToEdit.getTaskDeadline(), newStudents);
                model.setTask(taskToEdit, editedTask);
                model.updateGrades(taskToEdit, editedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Replaces the contents of the tutorial group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setTutorialGroups(List<TutorialGroup> groups) {
        this.tutorialGroups.setTutorialGroups(groups);
    }

    private void setGrades(ObservableMap<GradeKey, Grade> gradeMap) {
        this.grades.setGradeMapWithMap(gradeMap);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTasks(newData.getTaskList());
        setTutorialGroups(newData.getTutorialGroupList());
        setGrades(newData.getGradeMap());
    }

    //// person-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Find the student based on name
     * @return the student
     */
    public Student findStudent(String name) {
        return students.find(name);
    }

    //// task methods

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    // tutorial group level
    /**
     * Returns true if a tutorialGroup with the same identity as {@code tutorialGroup} exists in the address book.
     */
    public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        return tutorialGroups.contains(tutorialGroup);
    }

    /**
     * Returns true if a tutorialGroup with the same identity as {@code tutorialGroup} exists in the address book.
     */
    public boolean getTutorialGroup(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        return tutorialGroups.contains(tutorialGroup);
    }

    /**
     * Adds a tutorialGroup to the address book.
     * The tutorialGroup must not already exist in the address book.
     */
    public void addTutorialGroup(TutorialGroup toAdd) {
        tutorialGroups.add(toAdd);
    }


    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTutorialGroup(TutorialGroup key) {
        tutorialGroups.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        // return tasks.asUnmodifiableObservableList().size() + " tasks";
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroups.asUnmodifiableObservableList();
    }

    @Override
    public ObservableMap<GradeKey, Grade> getGradeMap() {
        return grades.asUnmodifiableObservableMap();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    public void addGrade(GradeKey gradeKey, Grade grade) {
        grades.add(gradeKey, grade);
    }

    /**
     * Updates the grade map by updating the tasks in the map.
     * @param taskToEdit the current task associated with student(s)
     * @param editedTask the task to be associated with taskToEdit's student(s) after the update
     */
    public void updateGrades(Task taskToEdit, Task editedTask) {
        Set<GradeKey> currentGradeKeys = grades.asUnmodifiableObservableMap().keySet();
        Set<GradeKey> badKeys = new HashSet<>();
        Map<GradeKey, Grade> toAdd = new HashMap<>();
        for (GradeKey key : currentGradeKeys) {
            if (key.task.isSameTask(taskToEdit)) {
                Grade grade = grades.get(key);
                toAdd.put(new GradeKey(key.student, editedTask), grade);
                badKeys.add(key);
            }
        }
        grades.removeAll(badKeys);
        grades.addAll(toAdd);
    }
}
