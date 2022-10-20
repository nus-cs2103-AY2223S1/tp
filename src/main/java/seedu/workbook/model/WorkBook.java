package seedu.workbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the WorkBook level
 * Duplicates are not allowed (by .isSameInternship comparison)
 */
public class WorkBook implements ReadOnlyWorkBook {

    private final UniqueInternshipList internships;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors.
     * See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use.
     * There are other ways to avoid duplication among constructors.
     */
    {
        internships = new UniqueInternshipList();
    }

    public WorkBook() {
    }

    /**
     * Creates an WorkBook using the Internships in the {@code toBeCopied}
     */
    public WorkBook(ReadOnlyWorkBook toBeCopied) {
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
     * Resets the existing data of this {@code WorkBook} with {@code newData}.
     */
    public void resetData(ReadOnlyWorkBook newData) {
        requireNonNull(newData);

        setInternships(newData.getInternshipList());
    }

    //// internship-level operations

    /**
     * Returns true if an internship with the same identity as {@code internship}
     * exists in the work book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    /**
     * Adds an internship to the work book.
     * The internship must not already exist in the work book.
     */
    public void addInternship(Internship i) {
        internships.add(i);
    }

    /**
     * Replaces the given internship {@code target} in the list with
     * {@code editedInternship}.
     * {@code target} must exist in the work book.
     * The internship identity of {@code editedInternship} must not be the same as
     * another existing internship in the work book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code WorkBook}.
     * {@code key} must exist in the work book.
     */
    public void removeInternship(Internship key) {
        internships.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return internships.asUnmodifiableObservableList().size() + " internship applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internships.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WorkBook // instanceof handles nulls
                        && internships.equals(((WorkBook) other).internships));
    }

    @Override
    public int hashCode() {
        return internships.hashCode();
    }
}
