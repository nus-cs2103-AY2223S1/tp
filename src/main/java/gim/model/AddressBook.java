package gim.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import gim.model.exercise.Exercise;
import gim.model.exercise.UniqueExerciseList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameExercise comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueExerciseList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueExerciseList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Exercises in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setExercises(List<Exercise> persons) {
        this.persons.setExercises(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasExercise(Exercise person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addExercise(Exercise p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedExercise} must not be the same as another existing person in the address book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        persons.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeExercise(Exercise key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
