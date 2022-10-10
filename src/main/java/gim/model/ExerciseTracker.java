package gim.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import gim.model.exercise.Exercise;
import gim.model.exercise.UniqueExerciseList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameExercise comparison)
 */
public class ExerciseTracker implements ReadOnlyExerciseTracker {

    private final UniqueExerciseList exercises;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        exercises = new UniqueExerciseList();
    }

    public ExerciseTracker() {}

    /**
     * Creates an ExerciseTracker using the Exercises in the {@code toBeCopied}
     */
    public ExerciseTracker(ReadOnlyExerciseTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code ExerciseTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyExerciseTracker newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
    }

    //// exercise-level operations

    /**
     * Returns true if an exercise with the same identity as {@code exercise} exists in the exercise tracker.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Adds an exercise to the exercise tracker.
     * The exercise must not already exist in the exercise tracker.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the exercise tracker.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise
     * in the exercise tracker.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code ExerciseTracker}.
     * {@code key} must exist in the exercise tracker.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exercises.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseTracker // instanceof handles nulls
                && exercises.equals(((ExerciseTracker) other).exercises));
    }

    @Override
    public int hashCode() {
        return exercises.hashCode();
    }
}
