package seedu.address.model.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ComparableByName;

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
            throw new DuplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the object {@code t} in the list with {@code editedT}.
     * {@code t} must exist in the list.
     * The text identity of {@code editedT} must not be the same as another existing object in the list.
     */
    public void setItem(T t, T editedT) {
        requireAllNonNull(t, editedT);

        int index = internalList.indexOf(t);
        if (index == -1) {
            throw new NotFoundException();
        }

        if (!t.hasSameName(editedT) && contains(editedT)) {
            throw new DuplicateException();
        }

        internalList.set(index, editedT);
    }

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException();
        }
    }

    public void setList(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code newList}.
     * {@code newList} must not contain duplicate objects.
     */
    public void setList(List<T> newList) {
        requireAllNonNull(newList);
        if (!itemsAreUnique(newList)) {
            throw new DuplicateException();
        }

        internalList.setAll(newList);
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
                && internalList.equals(((UniqueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tList} contains only unique objects.
     */
    private boolean itemsAreUnique(List<T> tList) {
        for (int i = 0; i < tList.size() - 1; i++) {
            for (int j = i + 1; j < tList.size(); j++) {
                if (tList.get(i).hasSameName(tList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
