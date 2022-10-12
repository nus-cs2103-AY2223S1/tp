package tuthub.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.UniqueTutorList;

/**
 * Wraps all data at the Tuthub level
 * Duplicates are not allowed (by .isSameTutor comparison)
 */
public class Tuthub implements ReadOnlyTuthub {

    private final UniqueTutorList tutors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tutors = new UniqueTutorList();
    }

    public Tuthub() {}

    /**
     * Creates an Tuthub using the Tutors in the {@code toBeCopied}
     */
    public Tuthub(ReadOnlyTuthub toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Tutor list with {@code Tutors}.
     * {@code Tutors} must not contain duplicate Tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        this.tutors.setTutors(tutors);
    }

    /**
     * Resets the existing data of this {@code Tuthub} with {@code newData}.
     */
    public void resetData(ReadOnlyTuthub newData) {
        requireNonNull(newData);

        setTutors(newData.getTutorList());
    }

    //// Tutor-level operations

    /**
     * Returns true if a Tutor with the same identity as {@code Tutor} exists in tuthub.
     */
    public boolean hasTutor(Tutor tutor) {
        requireNonNull(tutor);
        return tutors.contains(tutor);
    }

    /**
     * Adds a Tutor to tuthub.
     * The Tutor must not already exist in tuthub.
     */
    public void addTutor(Tutor p) {
        tutors.add(p);
    }

    /**
     * Replaces the given Tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in tuthub.
     * The Tutor identity of {@code editedTutor} must not be the same as another existing Tutor in tuthub.
     */
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireNonNull(editedTutor);

        tutors.setTutor(target, editedTutor);
    }

    /**
     * Removes {@code key} from this {@code Tuthub}.
     * {@code key} must exist in tuthub.
     */
    public void removeTutor(Tutor key) {
        tutors.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tutors.asUnmodifiableObservableList().size() + " Tutors";
        // TODO: refine later
    }

    @Override
    public ObservableList<Tutor> getTutorList() {
        return tutors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tuthub // instanceof handles nulls
                && tutors.equals(((Tuthub) other).tutors));
    }

    @Override
    public int hashCode() {
        return tutors.hashCode();
    }
}
