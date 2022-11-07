package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.util.exceptions.SortedListException;

/**
 * A list of Comparable Objects that enforces uniqueness between its elements and does not allow nulls.
 * An Object is considered unique by comparing using {@code T::equals}. As such, adding and updating of
 * the Object uses T::equals for equality to ensure that the Object being added or updated is
 * unique in terms of identity in the MaximumSortedList. The list also enforces a maximum number of
 * elements at any point in time and guarantees that all the elements are sorted by comparing using
 * {@code T::compareTo}.
 *
 * Supports a minimal set of list operations.
 */
public class MaximumSortedList<T extends Comparable<T>> {
    private final ObservableList<T> internalMaximumSortedList;
    private final int maxSize;

    /**
     * Creates a new MaximumSortedList object with the input
     * maximum size.
     *
     * @param maxSize the maximum number of elements allowed.
     */
    public MaximumSortedList(int maxSize) {
        internalMaximumSortedList = FXCollections.observableArrayList();
        this.maxSize = maxSize;
    }

    /**
     * Creates a new MaximumSortedList object with all the elements
     * of the input MaximumSortedList.
     *
     * @param previousMaximumSortedList the previous MaximumSortedList
     *        containing the elements to be added.
     */
    public MaximumSortedList(MaximumSortedList<T> previousMaximumSortedList) {
        this.maxSize = previousMaximumSortedList.maxSize;
        internalMaximumSortedList = FXCollections.observableArrayList();
        previousMaximumSortedList.forEach(internalMaximumSortedList::add);
    }

    public ObservableList<T> getObservableList() {
        ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalMaximumSortedList);
        return internalUnmodifiableList;
    }

    /**
     * Adds the specified element to the list according to the sorted order.
     *
     * @param t the Comparable to be added.
     * @return a boolean value describing whether the input element has been added.
     */
    public boolean add(T t) {
        requireNonNull(t);
        if (internalMaximumSortedList.size() >= maxSize || contains(t)) {
            return false;
        }
        internalMaximumSortedList.add(t);
        sort();
        return true;
    }

    /**
     * Returns the specified element to the list
     *
     * @param zeroBasedIndex the index of the element to get
     * @return a boolean value describing whether the input element has been added.
     */
    public T get(int zeroBasedIndex) throws SortedListException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= maxSize) {
            throw new SortedListException("Error: The list does not contain the input zeroBasedIndex");
        }
        return internalMaximumSortedList.get(zeroBasedIndex);
    }

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     *
     * @param zeroBasedIndex the zeroBasedIndex of the element to be removed.
     * @return the Comparable at the specified zeroBasedIndex.
     * @throws SortedListException the exception encountered when the input zeroBasedIndex
     *         is invalid.
     */
    public T remove(int zeroBasedIndex) throws SortedListException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= maxSize) {
            throw new SortedListException("Error: The list does not contain the input zeroBasedIndex");
        }
        return internalMaximumSortedList.remove(zeroBasedIndex);
    }

    /**
     * Removes the specified element in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     *
     * @param t the Comparable to be removed.
     * @return the specified Comparable.
     * @throws SortedListException the exception encountered when the list does not
     *         contain the specified element.
     */
    public T remove(T t) throws SortedListException {
        requireNonNull(t);
        int indexToRemove = -1;
        for (int i = 0; i < internalMaximumSortedList.size(); i++) {
            if (t.equals(internalMaximumSortedList.get(i))) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            throw new SortedListException("Error: The list does not contain the object");
        }
        return internalMaximumSortedList.remove(indexToRemove);
    }

    /**
     * Checks whether this list contains the specified element.
     *
     * @param t the Comparable to be checked against.
     * @return a boolean value describing whether the MaximumSortedList
     *         contains the input Comparable.
     */
    public boolean contains(T t) {
        requireNonNull(t);

        int indexOfElement = -1;
        for (int i = 0; i < internalMaximumSortedList.size(); i++) {
            if (t.equals(internalMaximumSortedList.get(i))) {
                indexOfElement = i;
                break;
            }
        }
        if (indexOfElement == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmpty() {
        return internalMaximumSortedList.isEmpty();
    }

    public void forEach(Consumer<? super T> consumer) {
        internalMaximumSortedList.forEach(consumer);
    }

    public int size() {
        return internalMaximumSortedList.size();
    }

    public Stream<T> stream() {
        return internalMaximumSortedList.stream();
    }

    private int binarySearch(T t) {
        int low = 0;
        int high = internalMaximumSortedList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            T currentValue = internalMaximumSortedList.get(mid);
            if (currentValue.equals(t)) {
                return mid;
            } else if (currentValue.compareTo(t) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private void sort() {
        internalMaximumSortedList.sort(T::compareTo);
    }

    @Override
    public int hashCode() {
        return internalMaximumSortedList.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MaximumSortedList<?>)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        MaximumSortedList<T> otherList = (MaximumSortedList) other;
        if (otherList.maxSize != this.maxSize || !isEqual(otherList)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isEqual(MaximumSortedList<T> other) {
        if (other.size() != this.internalMaximumSortedList.size()) {
            return false;
        }
        int listSize = internalMaximumSortedList.size();
        for (int i = 0; i < listSize; i++) {
            if (!this.internalMaximumSortedList.get(i).equals(other.internalMaximumSortedList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
