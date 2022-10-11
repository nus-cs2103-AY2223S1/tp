package seedu.address.model.iteration;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.iteration.exceptions.DuplicateIterationException;
import seedu.address.model.iteration.exceptions.IterationNotFoundException;

/**
 * A list of iterations that enforces uniqueness between its elements and does not allow nulls.
 * An iteration is considered unique by comparing using {@code Iteration#isSameIteration(Iteration)}. As such, adding
 * and updating of iterations uses Iteration#isSameIteration(Iteration) for equality so as to ensure that the
 * iteration being added or updated is unique in terms of identity in the UniqueIterationList. However, the removal of
 * an iteration uses Iteration#equals(Object) so as to ensure that the iteration with exactly the same fields will be
 * removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Iteration#isSameIteration(Iteration)
 */
public class UniqueIterationList implements Iterable<Iteration> {

    private final ObservableList<Iteration> internalList = FXCollections.observableArrayList();
    private final ObservableList<Iteration> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent iteration as the given argument.
     */
    public boolean contains(Iteration toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameIteration);
    }

    /**
     * Adds an iteration to the list.
     * The iteration must not already exist in the list.
     */
    public void add(Iteration toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateIterationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the iteration {@code target} in the list with {@code editedIteration}.
     * {@code target} must exist in the list.
     * The iteration identity of {@code editedIteration} must not be the same as another existing
     * iteration in the list.
     */
    public void setIteration(Iteration target, Iteration editedIteration) {
        requireAllNonNull(target, editedIteration);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new IterationNotFoundException();
        }

        if (!target.isSameIteration(editedIteration) && contains(editedIteration)) {
            throw new DuplicateIterationException();
        }

        internalList.set(index, editedIteration);
    }

    /**
     * Removes the equivalent iteration from the list.
     * The iteration must exist in the list.
     */
    public void remove(Iteration toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new IterationNotFoundException();
        }
    }

    public void setIterations(UniqueIterationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code iterations}.
     * {@code iterations} must not contain duplicate iterations.
     */
    public void setIterations(List<Iteration> iterations) {
        requireAllNonNull(iterations);
        if (!areIterationsUnique(iterations)) {
            throw new DuplicateIterationException();
        }

        internalList.setAll(iterations);
    }

    /**
     * Returns the number of iterations in the UniqueIterationList.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Iteration> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Iteration> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIterationList // instanceof handles nulls
                && internalList.equals(((UniqueIterationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code iterations} contains only unique iterations.
     */
    private boolean areIterationsUnique(List<Iteration> iterations) {
        int numIterations = iterations.size();
        for (int i = 0; i < numIterations - 1; i++) {
            Iteration currIteration = iterations.get(i);
            for (int j = i + 1; j < numIterations; j++) {
                if (currIteration.isSameIteration(iterations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
