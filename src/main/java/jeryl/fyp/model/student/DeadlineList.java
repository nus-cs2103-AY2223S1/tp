package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.model.student.exceptions.DeadlineNotFoundException;
import jeryl.fyp.model.student.exceptions.DuplicateDeadlineException;

/**
 * A list of deadlines that enforces uniqueness between its elements and does not allow nulls.
 * A deadline is considered unique by comparing using {@code Deadline#isSameDeadline(Deadline)}. As such, adding and
 * updating of Deadlines uses Deadline#isSameDeadline(Deadline) for equality so as to ensure that the deadline being
 * added or updated is unique in terms of identity in the UniqueDeadlineList. However, the removal of a deadline uses
 * Deadline#equals(Object) so as to ensure that the deadline with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Deadline#isSameDeadlineName(Deadline)
 */
public class DeadlineList implements DeadlineListTemplate {
    private final ObservableList<Deadline> internalList = FXCollections.observableArrayList();
    private final ObservableList<Deadline> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent deadline as the given argument.
     */
    @Override
    public boolean contains(Deadline toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeadlineName);
    }

    /**
     * Adds a deadline to the list.
     * The deadline must not already exist in the list.
     */
    @Override
    public void add(Deadline toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateDeadlineException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the deadline {@code target} in the list with {@code editedDeadline}.
     * {@code target} must exist in the list.
     * The deadline identity of {@code editedDeadline} must not be the same as another existing deadline in the list.
     */
    @Override
    public void setDeadline(Deadline target, Deadline editedDeadline) {
        requireAllNonNull(target, editedDeadline);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeadlineNotFoundException();
        }

        if (!target.isSameDeadlineName(editedDeadline) && contains(editedDeadline)) {
            throw new DuplicateDeadlineException();
        }

        internalList.set(index, editedDeadline);
    }

    /**
     * Removes the equivalent deadline from the list.
     * The deadline must exist in the list.
     */
    @Override
    public void remove(Deadline toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeadlineNotFoundException();
        }
    }

    /**
     *  Replaces the contents of this list with {@code replacement}.
     */
    @Override
    public void setDeadlines(jeryl.fyp.model.student.DeadlineList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code deadlines}.
     * @param deadlines must not contain duplicate deadlines.
     */
    @Override
    public void setDeadlines(List<Deadline> deadlines) {
        requireAllNonNull(deadlines);
        if (!deadlinesAreUnique(deadlines)) {
            throw new DuplicateDeadlineException();
        }

        internalList.setAll(deadlines);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    @Override
    public ObservableList<Deadline> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Deadline> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof jeryl.fyp.model.student.DeadlineList // instanceof handles nulls
                && internalList.equals(((jeryl.fyp.model.student.DeadlineList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code deadlines} contains only unique deadlines.
     */
    private boolean deadlinesAreUnique(List<Deadline> deadlines) {
        for (int i = 0; i < deadlines.size(); i++) {
            for (int j = i + 1; j < deadlines.size(); j++) {
                if (deadlines.get(i).isSameDeadlineName(deadlines.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns unique Deadline if {@code deadlines} contains the Deadline with the specified task name.
     */
    @Override
    public Deadline getDeadlineByName(String taskName) {
        Deadline deadline = null;
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getDeadlineName().equals(taskName)) {
                deadline = internalList.get(i);
            }
        }
        return deadline;
    }

    /**
     * Returns the index if {@code deadlines} contains the Deadline with the specified task name.
     */
    @Override
    public Index getIndexByName(String taskName) {
        int index = internalList.size();
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getDeadlineName().equals(taskName)) {
                index = i;
            }
        }
        return new Index(index);
    }

    // Pending implementation
    @Override
    public Deadline getDeadlineByRank(Integer rank) {
        requireAllNonNull(rank);
        if (rank > internalList.size()) {
            throw new DeadlineNotFoundException("Deadline specified not exist!");
        }
        return internalList.get(rank);
    }
}

