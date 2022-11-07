package seedu.trackascholar.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.trackascholar.model.applicant.exceptions.ApplicantNotFoundException;
import seedu.trackascholar.model.applicant.exceptions.DuplicateApplicantException;

/**
 * A list of applicants that enforces uniqueness between its elements and does not allow nulls.
 * An applicant is considered unique by comparing using {@code Applicant#isSameApplicant(Applicant)}. As such, adding
 * and updating of applicants uses Applicant#isSameApplicant(Applicant) for equality to ensure that the applicant
 * being added or updated is unique in terms of identity in the UniqueApplicantList. However, the removal of an
 * applicant uses Applicant#equals(Object) to ensure that the applicant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Applicant#isSameApplicant(Applicant)
 */
public class UniqueApplicantList implements Iterable<Applicant> {

    private final ObservableList<Applicant> internalList = FXCollections.observableArrayList();
    private final ObservableList<Applicant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent applicant as the given argument.
     */
    public boolean contains(Applicant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplicant);
    }

    /**
     * Checks if the list contains an applicant with the same name as the given one.
     *
     * @param toCheck the applicant to be compared with the rest of the list
     * @return applicant from the list with the same name
     */
    public Applicant findSimilarApplicant(Applicant toCheck) {
        requireNonNull(toCheck);
        assert this.contains(toCheck);

        Optional<Applicant> similarApplicant = internalList.stream().filter(toCheck::isSameApplicant).findFirst();
        return similarApplicant.get();
    }

    /**
     * Adds an applicant to the list.
     * The applicant must not already exist in the list.
     */
    public void add(Applicant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicantException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the list.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the list.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ApplicantNotFoundException();
        }

        if (!target.isSameApplicant(editedApplicant) && contains(editedApplicant)) {
            throw new DuplicateApplicantException();
        }

        internalList.set(index, editedApplicant);
    }

    /**
     * Removes the equivalent applicant from the list.
     * The applicant must exist in the list.
     */
    public void remove(Applicant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ApplicantNotFoundException();
        }
    }

    /**
     * Removes all applicants with matching application status from the list.
     */
    public void removeByStatus(ApplicationStatus statusToRemove) {
        requireNonNull(statusToRemove);
        for (int index = internalList.size() - 1; index >= 0; index--) {
            if (internalList.get(index).isMatchingApplicationStatus(statusToRemove)) {
                internalList.remove(index);
            }
        }
    }

    /**
     * Sorts the list with the given {@code comparator}.
     */
    public void sort(Comparator<Applicant> comparator) {
        requireNonNull(comparator);
        internalList.sort(comparator);
    }

    public void setApplicants(UniqueApplicantList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        requireAllNonNull(applicants);
        if (!applicantsAreUnique(applicants)) {
            throw new DuplicateApplicantException();
        }

        internalList.setAll(applicants);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Applicant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Applicant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueApplicantList // instanceof handles nulls
                        && internalList.equals(((UniqueApplicantList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code applicants} contains only unique applicants.
     */
    private boolean applicantsAreUnique(List<Applicant> applicants) {
        for (int i = 0; i < applicants.size() - 1; i++) {
            for (int j = i + 1; j < applicants.size(); j++) {
                if (applicants.get(i).isSameApplicant(applicants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
