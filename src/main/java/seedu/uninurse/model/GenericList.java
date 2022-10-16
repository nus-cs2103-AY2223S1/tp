package seedu.uninurse.model;

import java.util.List;

/**
 * A generic list interface for multi-valued {@code Person} attributes.
 * @param <T> The type of attribute stored in the list.
 */
public interface GenericList<T> {
    /**
     * Adds an element to the {@code GenericList<T>}.
     *
     * @param t The element to be added.
     * @return The updated {@code GenericList<T>}.
     */
    GenericList<T> add(T t);

    /**
     * Deletes an element from the {@code GenericList<T>}.
     *
     * @param index The index of the element to be deleted.
     * @return The updated {@code GenericList<T>}.
     */
    GenericList<T> delete(int index);

    /**
     * Edits an element in the {@code GenericList<T>}.
     *
     * @param index The index of the element to be edited.
     * @return The updated {@code GenericList<T>}.
     */
    GenericList<T> edit(int index, T t);

    T get(int index);

    /**
     * Returns the size of the list.
     */
    int size();

    /**
     * Returns true if the {@code GenericList<T>} is empty.
     */
    boolean isEmpty();

    /**
     * Returns the internal task list.
     */
    List<T> getInternalList();
}
