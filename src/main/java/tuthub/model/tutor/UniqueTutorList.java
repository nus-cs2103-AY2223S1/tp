package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuthub.model.tutor.exceptions.DuplicateTutorException;
import tuthub.model.tutor.exceptions.TutorNotFoundException;

/**
 * A list of tutors that enforces uniqueness between its elements and does not allow nulls.
 * A tutor is considered unique by comparing using {@code Tutor#isSameTutor(Tutor)}. As such, adding and updating of
 * Tutors uses Tutor#isSameTutor(Tutor) for equality so as to ensure that the tutor being added or updated is
 * unique in terms of identity in the UniqueTutorList. However, the removal of a tutor uses Tutor#equals(Object) so
 * as to ensure that the tutor with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tutor#isSameTutor(Tutor)
 */
public class UniqueTutorList implements Iterable<Tutor> {

    private final ObservableList<Tutor> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Tutor as the given argument.
     */
    public boolean contains(Tutor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutor);
    }

    /**
     * Adds a Tutor to the list.
     * The Tutor must not already exist in the list.
     */
    public void add(Tutor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in the list.
     * The Tutor identity of {@code editedTutor} must not be the same as another existing Tutor in the list.
     */
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireAllNonNull(target, editedTutor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorNotFoundException();
        }

        if (!target.isSameTutor(editedTutor) && contains(editedTutor)) {
            throw new DuplicateTutorException();
        }

        internalList.set(index, editedTutor);
    }

    /**
     * Removes the equivalent Tutor from the list.
     * The Tutor must exist in the list.
     */
    public void remove(Tutor toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorNotFoundException();
        }
    }

    public void setTutors(UniqueTutorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Tutors}.
     * {@code Tutors} must not contain duplicate Tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        requireAllNonNull(tutors);
        if (!TutorsAreUnique(tutors)) {
            throw new DuplicateTutorException();
        }

        internalList.setAll(tutors);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tutor> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tutor> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorList // instanceof handles nulls
                        && internalList.equals(((UniqueTutorList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Tutors} contains only unique Tutors.
     */
    private boolean TutorsAreUnique(List<Tutor> tutors) {
        for (int i = 0; i < tutors.size() - 1; i++) {
            for (int j = i + 1; j < tutors.size(); j++) {
                if (tutors.get(i).isSameTutor(tutors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
