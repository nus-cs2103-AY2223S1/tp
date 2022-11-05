package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls. A
 * person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such,
 * adding and updating of persons uses Person#isSamePerson(Person) for equality so as to ensure that
 * the person being added or updated is unique in terms of identity in the UniquePersonList.
 * However, the removal of a person uses Person#equals(Object) so as to ensure that the person with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class DisplayItemList<T extends DisplayItem> implements Iterable<T> {

    protected ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList = FXCollections
        .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::weaklyEqual);
    }

    /**
     * Returns the item in the list that is equal (but no necessarily the same object) as the given
     * item.
     *
     * @param item The item to compare equality against.
     * @return The item in the list which is equal to the given item
     */
    public T get(T item) throws ItemNotFoundException {
        requireNonNull(item);
        List<T> matchingItems = internalList.stream().filter(item::weaklyEqual).collect(Collectors.toList());
        if (matchingItems.size() == 0) {
            throw new ItemNotFoundException();
        }
        return matchingItems.get(0);
    }

    /**
     * Adds a person to the list. The person must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);
    }

    public <U extends T> void setItems(List<U> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }

        internalList.setAll(items);
    }

    public <U extends T> void setItems(DisplayItemList<U> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Removes the equivalent item from the list. The item must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * removes the element if predicate evals to true.
     *
     * @param predicate
     */
    public void removeIf(Predicate<T> predicate) {
        internalList.removeIf(predicate);
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DisplayItemList // instanceof handles nulls
                && internalList.equals(((DisplayItemList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void sort() {
        internalList.sort((arg0, arg1) -> arg0.getFullPath().toLowerCase().compareTo(arg1.getFullPath().toLowerCase()));
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private <U extends T> boolean itemsAreUnique(List<U> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).weaklyEqual(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
