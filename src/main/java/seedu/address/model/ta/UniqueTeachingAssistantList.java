package seedu.address.model.ta;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ta.exceptions.DuplicateTeachingAssistantException;

/**
 * A list of teaching assistants that enforces uniqueness between its elements and does not allow nulls.
 * A teaching assistants is considered unique by comparing using
 * {@code TeachingAssistant#isSameTeachingAssistant(TeachingAssistant)}.
 * As such, adding and updating of teaching assistants uses
 * TeachingAssistant#isSameTeachingAssistant(TeachingAssistant) for equality to
 * ensure that the teaching assistants being added or updated is unique in terms of identity in the
 * UniqueTeachingAssistantList.
 * However, the removal of a teaching assistant uses TeachingAssistant#equals(Object) to ensure
 * that the teaching assistant with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TeachingAssistant#isSameTeachingAssistant(TeachingAssistant)
 */
public class UniqueTeachingAssistantList implements Iterable<TeachingAssistant> {

    private final ObservableList<TeachingAssistant> internalList = FXCollections.observableArrayList();
    private final ObservableList<TeachingAssistant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial as the given argument.
     */
    public boolean contains(TeachingAssistant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeachingAssistant);
    }

    /**
     * Adds a tutorial to the list.
     * The tutorial must not already exist in the list.
     */
    public void add(TeachingAssistant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeachingAssistantException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate tutorials.
     */
    public void setTeachingAssistants(List<TeachingAssistant> teachingAssistants) {
        requireAllNonNull(teachingAssistants);
        if (!teachingAssistantsAreUnique(teachingAssistants)) {
            throw new DuplicateTeachingAssistantException();
        }

        internalList.setAll(teachingAssistants);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TeachingAssistant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TeachingAssistant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTeachingAssistantList // instanceof handles nulls
                && internalList.equals(((UniqueTeachingAssistantList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tutorials} contains only unique tutorials.
     */
    private boolean teachingAssistantsAreUnique(List<TeachingAssistant> teachingAssistants) {
        for (int i = 0; i < teachingAssistants.size() - 1; i++) {
            for (int j = i + 1; j < teachingAssistants.size(); j++) {
                if (teachingAssistants.get(i).isSameTeachingAssistant(teachingAssistants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
