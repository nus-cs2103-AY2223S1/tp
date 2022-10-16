package seedu.taassist.model.uniquelist;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.model.uniquelist.exceptions.DuplicateElementException;
import seedu.taassist.model.uniquelist.exceptions.ElementNotFoundException;

/**
 * A generic list of {@code Identity<T>} elements that enforces uniqueness between its elements and doesn't allow nulls.
 * An element is considered unique by comparing using {@code Identity<T>#isSame(T)}.
 * As such, adding and updating of elements uses {@code Identity<T>#isSame(T)} for equality so as to ensure that the
 * element being added or updated is unique in terms of identity in the UniqueList. However, the removal of an element
 * uses {@code Identity<T>#equals(Object)} so as to ensure that the element with exactly the same fields are removed.
 * Supports a minimal set of list operations.
 */
public class UniqueList<T extends Identity<T>> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent element as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Returns true if the list contains an element that matches the given {@code predicate}.
     */
    public boolean containsMatch(Predicate<T> predicate) {
        requireNonNull(predicate);
        return internalList.stream().anyMatch(predicate);
    }


    /**
     * Adds an element to the list.
     * The element must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateElementException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the element {@code target} in the list with {@code editedElement}.
     * {@code target} must exist in the list.
     * The identity of {@code editedElement} must not be the same as another existing element in the list.
     */
    public void setElement(T target, T editedElement) {
        requireAllNonNull(target, editedElement);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (!target.isSame(editedElement) && contains(editedElement)) {
            throw new DuplicateElementException();
        }

        internalList.set(index, editedElement);
    }

    /**
     * Removes the equivalent element from the list.
     * The element must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     */
    public void setElements(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code elements}.
     * Duplicates in {@code elements} will be removed.
     */
    public void setElements(List<T> elements) {
        requireAllNonNull(elements);
        Set<T> uniqueElements = new HashSet<>(elements);
        // Guaranteed to be unique
        internalList.setAll(uniqueElements);
    }

    /**
     * Finds and returns an element that has the same identity as {@code toFind}.
     */
    public T findElement(T toFind) {
        requireNonNull(toFind);
        return internalList.stream()
                .filter(toFind::isSame)
                .findFirst()
                .orElseThrow(ElementNotFoundException::new);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueList<?> // instanceof handles nulls
            && internalList.equals(((UniqueList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
