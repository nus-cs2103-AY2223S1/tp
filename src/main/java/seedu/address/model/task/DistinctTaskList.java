package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.model.task.exceptions.WrongTaskModifiedException;

/**
 * This class represents a list which contains Tasks objects which are distinct from
 * each other. Tasks are distinct from each other when they have different descriptions and
 * module codes.
 */
public class DistinctTaskList implements Iterable<Task> {

    public final ObservableList<Task> taskList = FXCollections.observableArrayList();
    public final ObservableList<Task> unmodifiableTaskList = FXCollections
            .unmodifiableObservableList(taskList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return taskList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Returns true if the list contains a task with an equivalent module as the given argument.
     */
    public boolean containsModule(Module toCheck) {
        requireNonNull(toCheck);
        return taskList.stream().map(Task::getModule).anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds the task to the taskList.
     * The task must not already exist in the list.
     *
     * @param taskAdded The task to be added.
     */
    public void addTask(Task taskAdded) {
        requireNonNull(taskAdded);
        //Might have to add address book as param to check if addressBook already has module
        if (contains(taskAdded)) {
            throw new DuplicateTaskException();
        }
        taskList.add(taskAdded);
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

        int index = taskList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (isSameTask && !target.isSameTask(editedTask)) {
            throw new WrongTaskModifiedException();
        }

        boolean isDuplicateTask = contains(editedTask) && !editedTask.isSameTask(target);
        if (!isSameTask && isDuplicateTask) {
            throw new DuplicateTaskException();
        }

        taskList.set(index, editedTask);
    }

    /**
     * Unlinks all tasks that are currently linked to {@code exam} and returns a List of these tasks.
     * @param exam the exam for the tasks to be unlinked from
     */
    public List<Task> unlinkTasksFromExam(Exam exam) {
        List<Task> matchedTasks = new ArrayList<Task>();
        taskList.forEach(task-> {
            if (task.isLinked() && task.getExam().equals(exam)) {
                Task unlinkedTask = task.unlinkTask();
                matchedTasks.add(unlinkedTask);
                replaceTask(task, unlinkedTask, true);
            }
        });
        return matchedTasks;
    }

    /**
     * Returns true if {@code examToEdit} is linked to any task, otherwise false.
     */
    public boolean isExamLinkedToTask(Exam exam) {
        requireNonNull(exam);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isLinked() && taskList.get(i).getExam().equals(exam)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces task by changing its given exam field from {@code previousExam}
     * to {@code newExam} for tasks that have their exam field as {@code previousExam}.
     * @param previousExam The exam in the task's exam field.
     * @param newExam The new exam which will replace the previous exam in the task's exam field.
     */
    public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
        requireAllNonNull(previousExam, newExam);
        taskList.forEach(task-> {
            if (task.isLinked() && task.getExam().equals(previousExam)) {
                Task editedTask = task.linkTask(newExam);
                replaceTask(task, editedTask, true);
            }
        });
    }

    /**
     * Replaces task by changing its given module field from {@code previousModule}
     * to {@code newModule} for tasks that have their module field as {@code previousModule}.
     * @param previousModule The module in the task's module field.
     * @param newModule The new module which will replace the previous module in the task's module field.
     */
    public void updateModuleFieldForTask(Module previousModule, Module newModule) {
        requireAllNonNull(previousModule, newModule);
        taskList.forEach(task-> {
            if (task.getModule().equals(previousModule)) {
                Task editedTask = task.editWithoutUnlinkingExam(newModule, null);
                replaceTask(task, editedTask, false);
            }
        });
    }

    /**
     * Links all tasks in {@code tasks} to {@code exam}.
     * @param exam The exam to link to.
     * @param tasks The list of tasks to link each task to {@code exam}.
     */
    public void linkTasksToExam(Exam exam, List<Task> tasks) {
        tasks.forEach(task-> {
            Task linkedTask = task.linkTask(exam);
            replaceTask(task, linkedTask, true);
        });
    }

    /**
     * Removes the equivalent task from the tasklist.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!taskList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Removes tasks that have their module field as {@code module} from the tasklist.
     * @param module The module in the task's module field.
     */
    public void deleteTasksWithModule(Module module) {
        requireAllNonNull(module);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getModule().equals(module)) {
                remove(task);
                --i;
            }
        }
    }


    public int getNumOfCompletedModuleTasks(Module module) {
        requireNonNull(module);
        return (int) taskList.stream().filter(Task::isComplete).map(Task::getModule)
            .filter(module::isSameModule).count();
    }

    public int getTotalNumOfModuleTasks(Module module) {
        requireNonNull(module);
        return (int) taskList.stream().map(Task::getModule).filter(module::isSameModule).count();
    }

    public int getNumOfCompletedExamTasks(Exam exam) {
        requireNonNull(exam);
        return (int) taskList.stream().filter(Task::isComplete).map(Task::getExam)
            .filter(exam::isSameExam).count();
    }

    public int getTotalNumOfExamTasks(Exam exam) {
        requireNonNull(exam);
        return (int) taskList.stream().map(Task::getExam).filter(exam::isSameExam).count();
    }

    /**
     * Sorts the tasks stored in the task list.
     *
     * @param criteria The criteria used for sorting.
     */
    public void sortTasks(Criteria criteria) {

        //@@author dlimyy-reused
        //Reused from https://stackoverflow.com/questions/51186174/
        //with slight modifications
        switch (criteria.getCriteria().toLowerCase()) {
        case "priority":
            FXCollections.sort(taskList, Comparator.comparing(Task::getPriorityTag,
                    Comparator.nullsLast(Comparator.naturalOrder())));
            break;
        case "deadline":
            FXCollections.sort(taskList, Comparator.comparing(Task::getDeadlineTag,
                    Comparator.nullsLast(Comparator.naturalOrder())));
            break;
        case "module":
            FXCollections.sort(taskList, Comparator.comparing(Task::getModule,
                    Comparator.naturalOrder()));
            break;
        case "description":
            FXCollections.sort(taskList, Comparator.comparing(Task::getDescription,
                    Comparator.naturalOrder()));
            break;
        default:
            break;
        }
        //@@author
    }

    /**
     * Checks whether the criteria given by the user is valid.
     *
     * @param criteria The criteria that is being checked for validity.
     * @return true if the criteria is valid; else return false.
     */
    public static boolean isValidCriteria(String criteria) {
        return criteria.equalsIgnoreCase("priority")
                || criteria.equalsIgnoreCase("deadline")
                || criteria.equalsIgnoreCase("module")
                || criteria.equalsIgnoreCase("description");
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    @Override
    public boolean equals(Object otherTask) {
        return otherTask == this
                || (otherTask instanceof DistinctTaskList
                && taskList.equals(((DistinctTaskList) otherTask).taskList));
    }

    public ObservableList<Task> getUnmodifiableTaskList() {
        return unmodifiableTaskList;
    }

    public void setTasks(List<Task> tasks) {
        requireNonNull(tasks);
        taskList.setAll(tasks);
    }
}
