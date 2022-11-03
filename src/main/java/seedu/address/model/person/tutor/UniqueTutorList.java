package seedu.address.model.person.tutor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.tutor.exceptions.DuplicateTutorException;
import seedu.address.model.person.tutor.exceptions.TutorNotFoundException;

/**
 * A list of tutors that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueTutorList implements Iterable<Tutor> {

    public final ObservableList<Tutor> internalList = FXCollections.observableArrayList();
    public final ObservableList<Tutor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutor as the given argument.
     */
    public boolean contains(Tutor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a tutor to the list.
     * The tutor must not already exist in the list.
     */
    public void add(Tutor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorException();
        }
        toAdd.updateTimeAddedToList();
        internalList.add(toAdd);
    }

    /**
     * Replaces the tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in the list.
     * The tutor identity of {@code editedTutor} must not be the same as another existing tutor in the list.
     */
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireAllNonNull(target, editedTutor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorNotFoundException();
        }

        if (!target.isSamePerson(editedTutor) && contains(editedTutor)) {
            throw new DuplicateTutorException();
        }

        internalList.set(index, editedTutor);
    }

    /**
     * Removes the equivalent tutor from the list.
     * The tutor must exist in the list.
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
     * Replaces the contents of this list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicateTutorException();
        }

        this.internalList.setAll(tutors);
    }

    /**
     * Sorts the list accordingly.
     * @param sortBy The method to sort the list by.
     */
    public void sort(SortCommand.SortBy sortBy) {
        switch (sortBy) {
        case ALPHA:
            internalList.sort(Comparator.comparing(person -> person.getName().fullName));
            break;
        case REVERSE:
            FXCollections.reverse(internalList);
            break;
        default:
            internalList.sort((first, second) -> {
                HashMap<Integer, Object> a = first.getUniqueId();
                HashMap<Integer, Object> b = second.getUniqueId();
                Instant t = (Instant) a.get(0);
                int result = t.compareTo((Instant) b.get(0));
                if (result == 0) {
                    return ((int) a.get(1)) - ((int) b.get(1));
                }
                return result;
            });
        }
    }

    /**
     * The backing list as an unmodifiable {@code ObservableList}.
     *
     * @return The backing list.
     */
    public ObservableList<Tutor> asUnmodifiableObservableTutorList() {
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
     * Returns true if {@code tutors} contains only unique tutors.
     */
    private boolean tutorsAreUnique(List<Tutor> tutors) {
        for (int i = 0; i < tutors.size() - 1; i++) {
            for (int j = i + 1; j < tutors.size(); j++) {
                if (tutors.get(i).isSamePerson(tutors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
