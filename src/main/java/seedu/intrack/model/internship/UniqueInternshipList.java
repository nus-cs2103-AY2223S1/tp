package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intrack.model.tag.exceptions.DuplicateInternshipException;
import seedu.intrack.model.tag.exceptions.InternshipNotFoundException;

/**
 * A list of internships that enforces uniqueness between its elements and does not allow nulls.
 * An internship is considered unique by comparing using {@code Internship#isSameInternship(Internship)}. As such,
 * adding and updating of internships uses Internship#isSameInternship(Internship) for equality so as to ensure that
 * the internship being added or updated is unique in terms of identity in the UniqueInternshipList. However, the
 * removal of an internship uses Internship#equals(Object) so as to ensure that the internship with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Internship#isSameInternship(Internship)
 */
public class UniqueInternshipList implements Iterable<Internship> {

    private final ObservableList<Internship> internalList = FXCollections.observableArrayList();
    private final ObservableList<Internship> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent internship as the given argument.
     */
    public boolean contains(Internship toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInternship);
    }

    /**
     * Adds an internship to the list.
     * The internship must not already exist in the list.
     */
    public void add(Internship toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateInternshipException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the list.
     * The internship identity of {@code editedInternship} must not be the same as another existing internship in the
     * list.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InternshipNotFoundException();
        }

        if (!target.isSameInternship(editedInternship) && contains(editedInternship)) {
            throw new DuplicateInternshipException();
        }

        internalList.set(index, editedInternship);
    }

    /**
     * Removes the equivalent internship from the list.
     * The internship must exist in the list.
     */
    public void remove(Internship toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InternshipNotFoundException();
        }
    }

    public void setInternships(UniqueInternshipList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Internship> internships) {
        requireAllNonNull(internships);
        if (!internshipsAreUnique(internships)) {
            throw new DuplicateInternshipException();
        }

        internalList.setAll(internships);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Internship> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Internship> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueInternshipList // instanceof handles nulls
                        && internalList.equals(((UniqueInternshipList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code internships} contains only unique internships.
     */
    private boolean internshipsAreUnique(List<Internship> internships) {
        for (int i = 0; i < internships.size() - 1; i++) {
            for (int j = i + 1; j < internships.size(); j++) {
                if (internships.get(i).isSameInternship(internships.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts all the {@code internships} in the list by their respective tasks
     * with the nearest date and time in ascending order
     */
    public void dateSortAscending() {
        ObservableList<Internship> nonOrExpiredDates = FXCollections.observableArrayList();
        ObservableList<Internship> copyInternalList = FXCollections.observableArrayList();
        LocalDateTime currentTime = LocalDateTime.now();
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getTasks().size() == 0) {
                nonOrExpiredDates.add(internalList.get(i));
            } else if (internalList.get(i).getNearestTaskDate().isBefore(currentTime)) {
                nonOrExpiredDates.add(internalList.get(i));
            } else {
                copyInternalList.add(internalList.get(i));
            }
        }
        copyInternalList.sort(Comparator.comparing(o -> o.getNearestTaskDate()));
        for (int i = 0; i < nonOrExpiredDates.size(); i++) {
            copyInternalList.add(nonOrExpiredDates.get(i));
        }
        FXCollections.copy(internalList, copyInternalList);
    }

    /**
     * Sorts all the {@code internships} in the list by their respective tasks
     * with the nearest date and time in descending order
     */
    public void dateSortDescending() {
        ObservableList<Internship> nonOrExpiredDates = FXCollections.observableArrayList();
        ObservableList<Internship> copyInternalList = FXCollections.observableArrayList();
        LocalDateTime currentTime = LocalDateTime.now();
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getTasks().size() == 0) {
                nonOrExpiredDates.add(internalList.get(i));
            } else if (internalList.get(i).getNearestTaskDate().isBefore(currentTime)) {
                nonOrExpiredDates.add(internalList.get(i));
            } else {
                copyInternalList.add(internalList.get(i));
            }
        }
        copyInternalList.sort(Comparator.comparing(o -> o.getNearestTaskDate()));
        Collections.reverse(copyInternalList);
        for (int i = 0; i < nonOrExpiredDates.size(); i++) {
            copyInternalList.add(nonOrExpiredDates.get(i));
        }
        FXCollections.copy(internalList, copyInternalList);
    }

    /**
     * Sorts all the {@code Internship} in the list by their respective salaries in ascending order
     */
    public void salarySortAscending() {
        internalList.sort(Comparator.comparing(o -> Integer.parseInt(o.getSalary().value)));
    }

    /**
     * Sorts all the {@code Internship} in the list by their respective salaries in descending order
     */
    public void salarySortDescending() {
        internalList.sort(Comparator.comparing(o -> Integer.parseInt(o.getSalary().value)));
        Collections.reverse(internalList);
    }
}
