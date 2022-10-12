package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.UniqueResidentList;

/**
 * Wraps all data at the RC4HDB level
 * Duplicates are not allowed (by .isSameResident comparison)
 */
public class ResidentBook implements ReadOnlyResidentBook {

    private final UniqueResidentList residents;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        residents = new UniqueResidentList();
    }

    public ResidentBook() {}

    /**
     * Creates an ResidentBook using the Residents in the {@code toBeCopied}
     */
    public ResidentBook(ReadOnlyResidentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the resident list with {@code residents}.
     * {@code residents} must not contain duplicate residents.
     */
    public void setResidents(List<Resident> residents) {
        this.residents.setResidents(residents);
    }

    /**
     * Resets the existing data of this {@code ResidentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyResidentBook newData) {
        requireNonNull(newData);

        setResidents(newData.getResidentList());
    }

    //// resident-level operations

    /**
     * Returns true if a resident with the same identity as {@code resident} exists in the resident book.
     */
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return residents.contains(resident);
    }

    /**
     * Adds a resident to the resident book.
     * The resident must not already exist in the resident book.
     */
    public void addResident(Resident p) {
        residents.add(p);
    }

    /**
     * Replaces the given resident {@code target} in the list with {@code editedResident}.
     * {@code target} must exist in the resident book.
     * The resident identity of {@code editedResident} must not be the same as another existing resident in the
     * resident book.
     */
    public void setResident(Resident target, Resident editedResident) {
        requireNonNull(editedResident);

        residents.setResident(target, editedResident);
    }

    /**
     * Removes {@code key} from this {@code ResidentBook}.
     * {@code key} must exist in the resident book.
     */
    public void removeResident(Resident key) {
        residents.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return residents.asUnmodifiableObservableList().size() + " residents";
        // TODO: refine later
    }

    public ObservableList<Resident> getResidentList() {
        return residents.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidentBook // instanceof handles nulls
                && residents.equals(((ResidentBook) other).residents));
    }

    @Override
    public int hashCode() {
        return residents.hashCode();
    }
}
