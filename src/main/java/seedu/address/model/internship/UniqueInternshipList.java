package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.model.internship.exceptions.InternshipNotFoundException;

/**
 * A list of internships that enforces uniqueness between its elements and does not allow nulls.
 * An internship is considered unique by comparing using {@code Internship#isSameInternship(Internship)}.
 * As such, adding and updating of internships uses Internship#isSameInternship(Internship) for equality
 * to ensure that the internship being added or updated is unique in terms of identity in the UniqueInternshipList.
 * However, the removal of an internship uses Internship#equals(Object)
 * to ensure that the internship with exactly the same fields will be removed.
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
     * Returns an Internship with id that matches idToFind.
     * If none is found, return null.
     */
    public Internship findById(InternshipId idToFind) {
        return internalList
                .stream()
                .filter(i -> i != null && i.getInternshipId().equals(idToFind))
                .findFirst()
                .orElse(null);
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
     * The internship identity of {@code editedInternship}
     * must not be the same as another existing internship in the list.
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
}
