package swift.model.bridge;

import static java.util.Objects.requireNonNull;
import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swift.model.bridge.exceptions.BridgeNotFoundException;
import swift.model.bridge.exceptions.DuplicateBridgeException;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Represents a list of person-task bridges.
 */
public class PersonTaskBridgeList implements Iterable<PersonTaskBridge> {

    private final ObservableList<PersonTaskBridge> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonTaskBridge> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person-task bridge as the
     * given argument.
     *
     * @param toCheck The person-task bridge to check for.
     */
    public boolean contains(PersonTaskBridge toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person-task bridge to the list.
     * The person-task bridge must not already exist in the list.
     *
     * @param toAdd The person-task bridge to add.
     */
    public void add(PersonTaskBridge toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBridgeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent person-task bridge from the list.
     * The person-task bridge must exist in the list.
     *
     * @param toRemove The person-task bridge to be removed.
     */
    public void remove(PersonTaskBridge toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BridgeNotFoundException();
        }
    }

    /**
     * Removes all person-task bridges associated with a person from the list.
     *
     * @param person The person whose person-task bridges are to be removed.
     */
    public void removePerson(Person person) {
        requireNonNull(person);
        internalList.removeIf(bridge -> bridge.getPersonId().equals(person.getId()));
    }

    /**
     * Removes all person-task bridges associated with a task from the list.
     *
     * @param task The task whose person-task bridges are to be removed.
     */
    public void removeTask(Task task) {
        requireNonNull(task);
        internalList.removeIf(bridge -> bridge.getTaskId().equals(task.getId()));
    }

    /**
     * Replaces the contents of this list with {@code bridges}.
     * {@code bridges} must not contain duplicate bridges.
     *
     * @param bridges The list of bridges to replace the current list with.
     */
    public void setBridges(List<PersonTaskBridge> bridges) {
        requireAllNonNull(bridges);
        if (!bridgesAreUnique(bridges)) {
            throw new DuplicateBridgeException();
        }

        internalList.setAll(bridges);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonTaskBridge> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonTaskBridge> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTaskBridgeList // instanceof handles nulls
                        && internalList.equals(((PersonTaskBridgeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code bridges} contains only unique bridges.
     *
     * @param bridges A list of bridges.
     */
    private boolean bridgesAreUnique(List<PersonTaskBridge> bridges) {
        for (int i = 0; i < bridges.size() - 1; i++) {
            for (int j = i + 1; j < bridges.size(); j++) {
                if (bridges.get(i).equals(bridges.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
