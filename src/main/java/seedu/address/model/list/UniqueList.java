package seedu.address.model.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ComparableByName;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueList<T extends ComparableByName<T>> implements Iterable<T> {


    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent object (by comparing name) as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasSameName);
    }

    /**
     * Adds an object T to the list.
     * The object must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateProjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the list.
     * The project identity of {@code editedProject} must not be the same as another existing project in the list.
     */
    public void setProject(T target, T editedProject) {
        requireAllNonNull(target, editedProject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ProjectNotFoundException();
        }

        if (!target.hasSameName(editedProject) && contains(editedProject)) {
            throw new DuplicateProjectException();
        }

        internalList.set(index, editedProject);
    }

    /**
     * Removes the equivalent project from the list.
     * The project must exist in the list.
     */
    public void remove(Project toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProjectNotFoundException();
        }
    }

    public void setProjects(UniqueProjectList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        requireAllNonNull(projects);
        if (!projectsAreUnique(projects)) {
            throw new DuplicateProjectException();
        }

        internalList.setAll(projects);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Project> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Project> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueProjectList // instanceof handles nulls
                && internalList.equals(((UniqueProjectList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code projects} contains only unique projects.
     */
    private boolean projectsAreUnique(List<Project> projects) {
        for (int i = 0; i < projects.size() - 1; i++) {
            for (int j = i + 1; j < projects.size(); j++) {
                if (projects.get(i).hasSameName(projects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
