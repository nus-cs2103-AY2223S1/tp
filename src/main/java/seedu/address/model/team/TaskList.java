package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TaskNotFoundException;

/**
 * A list of tasks to be completed by the team.
 * Supports a minimal set of list operations.
 */
public class TaskList implements Iterable<Task> {
    public static final String NO_TASKS = "No Tasks Available";

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.equals(editedTask) && contains(editedTask)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(List<Task> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement);
    }

    /**
     * Returns a map representing the number of tasks assigned to each person.
     * @return Map of person to number of tasks assigned
     */
    public Map<Person, Integer> getTasksPerPerson() {
        HashMap<Person, Integer> assignments = new HashMap<>();
        for (Task t : internalList) {
            for (Person p : t.getAssignees()) {
                assignments.put(p, assignments.getOrDefault(p, 0) + 1);
            }
        }
        return assignments;
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskList // instanceof handles nulls
            && internalList.equals(((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        if (internalList.isEmpty()) {
            //Set it to an empty string for now so that the team commands will only show the name of the team.
            return ""; //return NO_TASKS;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : internalList) {
            sb.append(String.format("\t%d. %s\n", i, task));
            i++;
        }
        return sb.toString();
    }
}

