package seedu.phu.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the internship-book level
 * Duplicates are not allowed (by .isSameInternship comparison)
 */
public class InternshipBook implements ReadOnlyInternshipBook {

    private final UniqueInternshipList internships;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internships = new UniqueInternshipList();
    }

    public InternshipBook() {}

    /**
     * Creates an InternshipBook using the Internships in the {@code toBeCopied}
     */
    public InternshipBook(ReadOnlyInternshipBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Internship> internships) {
        this.internships.setInternships(internships);
    }

    /**
     * Resets the existing data of this {@code InternshipBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipBook newData) {
        requireNonNull(newData);

        setInternships(newData.getInternshipList());
    }

    //// internship-level operations

    /**
     * Returns true if a internship with the same identity as {@code internship} exists in the internship book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    /**
     * Adds a internship to the internship book.
     * The internship must not already exist in the internship book.
     */
    public void addInternship(Internship p) {
        internships.add(p);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the internship book.
     * The internship identity of {@code editedInternship} must not be the same as another
     * existing internship in the internship book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code InternshipBook}.
     * {@code key} must exist in the internship book.
     */
    public void removeInternship(Internship key) {
        internships.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return internships.asUnmodifiableObservableList().size() + " internships";
        // TODO: refine later
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internships.asUnmodifiableObservableList();
    }

    @Override
    public void sortInternshipList(Comparator<Internship> comparator) {
        internships.sortInternship(comparator);
    }

    @Override
    public void reverseList() {
        internships.reverseList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipBook // instanceof handles nulls
                && internships.equals(((InternshipBook) other).internships));
    }

    @Override
    public int hashCode() {
        return internships.hashCode();
    }
}
