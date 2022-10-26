package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the FindMyIntern level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FindMyIntern implements ReadOnlyFindMyIntern {

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

    public FindMyIntern() {}

    /**
     * Creates an FindMyIntern using the Internships in the {@code toBeCopied}
     */
    public FindMyIntern(ReadOnlyFindMyIntern toBeCopied) {
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
     * Resets the existing data of this {@code FindMyIntern} with {@code newData}.
     */
    public void resetData(ReadOnlyFindMyIntern newData) {
        requireNonNull(newData);

        setInternships(newData.getInternshipList());
    }

    //// internship-level operations

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in findMyIntern.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    /**
     * Adds an internship to findMyIntern.
     * The internship must not already exist in findMyIntern.
     */
    public void addInternship(Internship p) {
        internships.add(p);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in findMyIntern.
     * The internship identity of {@code editedInternship} must not be the same as
     * another existing internship in findMyIntern.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code FindMyIntern}.
     * {@code key} must exist in findMyIntern.
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMyIntern // instanceof handles nulls
                && internships.equals(((FindMyIntern) other).internships));
    }

    @Override
    public int hashCode() {
        return internships.hashCode();
    }
}
