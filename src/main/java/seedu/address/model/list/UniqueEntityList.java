package seedu.address.model.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interfaces.ComparableByName;
import seedu.address.model.interfaces.HasIntegerIdentifier;

/**
 * A class for a list of unique entities.
 * Notably, entities need to extend {@link ComparableByName} and {@link HasIntegerIdentifier}.
 * @param <T> Type of entity in list
 */
public class UniqueEntityList<T extends ComparableByName<T> & HasIntegerIdentifier> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent object (by comparing name) as the given argument.
     */
    public boolean containsByName(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasSameName);
    }

    /**
     * Returns true if the list contains the given ID.
     */
    public boolean containsId(int id) {
        return internalList.stream().anyMatch((item) -> id == item.getId());
    }

    /**
     * Adds an object T to the list.
     * The object must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (containsByName(toAdd)) {
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

        if (!t.hasSameName(editedT) && containsByName(editedT)) {
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

    public void setList(UniqueEntityList<T> replacement) {
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

    /**
     * Returns the backing list as a modifiable {@code ObservableList}.
     */
    public ObservableList<T> asModifiableObservableList() {
        return internalList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntityList<?> // instanceof handles nulls
                && internalList.equals(((UniqueEntityList) other).internalList));
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



    /**
     * Get an element by its id
     * @param id id to retrieve with
     * @return an element if it is found.
     * @throws NotFoundException if element is not found.
     */
    public T getElementById(int id) {
        for (T t: this) {
            if (t.getId() == id) {
                return t;
            }
        }
        throw new NotFoundException();
    }

    public void sortById() {
        internalList.sort(Comparator.comparingInt(HasIntegerIdentifier::getId));
    }
}
