package seedu.pennywise.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pennywise.model.entry.exceptions.DuplicateEntryException;
import seedu.pennywise.model.entry.exceptions.EntryNotFoundException;

/**
 * A list of entries that enforces uniqueness between its elements and does not allow nulls.
 * An entry is considered unique by comparing using {@link Entry#isSameEntry(Entry)}. As such, adding and updating of
 * entries uses {@link Entry#isSameEntry(Entry)} for equality to ensure that the entry being added or updated is
 * unique in terms of identity in the UniqueEntryList. However, the removal of an entry uses
 * {@link Entry#equals(Object)} to ensure that the entry with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 * </p>
 *
 * @see Entry#isSameEntry(Entry)
 */
public class UniqueEntryList implements Iterable<Entry> {

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entry> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent {@code Entry} as the given argument.
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Adds an {@code Entry} to the list.
     * Precondition: The entry must not already exist in the list.
     */
    public void add(Entry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent {@code Entry} from the list.
     * Precondition: The entry must exist in the list.
     */
    public void remove(Entry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * Precondition: The entry identity of {@code editedEntry} must not be the same as another existing entry in the
     * list.
     */
    public void setEntries(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (!target.isSameEntry(editedEntry) && contains(editedEntry)) {
            throw new DuplicateEntryException();
        }

        internalList.set(index, editedEntry);
    }

    public void setEntries(UniqueEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */
    public void setEntries(List<Entry> entries) {
        requireAllNonNull(entries);
        if (!entriesAreUnique(entries)) {
            throw new DuplicateEntryException();
        }

        internalList.setAll(entries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Entry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueEntryList // instanceof handles nulls
            && internalList.equals(((UniqueEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entries} contains only unique entries.
     */
    private boolean entriesAreUnique(List<Entry> entries) {
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).isSameEntry(entries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
