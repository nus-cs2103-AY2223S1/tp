package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of exercises that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 */
public class ExerciseList implements Iterable<Exercise> {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exercise> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final ObservableList<Exercise> displayedList = FXCollections.observableArrayList(internalUnmodifiableList);

    /**
     * Returns true if the list contains an equivalent exercise as the given argument.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExercise);
    }

    /**
     * Adds an Exercise to the List and exerciseHashMap.
     * If the Exercise already exists, i.e. two Exercises with the same Name, categorise them together.
     */
    public void add(Exercise toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
        displayedList.setAll(internalUnmodifiableList);
    }

    /**
     * Replaces the Exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the list.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        // Might be removing this?
        requireAllNonNull(target, editedExercise);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExerciseNotFoundException();
        }
        internalList.set(index, editedExercise);
    }

    /**
     * Removes the equivalent exercise from the list.
     * The exercise must exist in the list.
     */
    public void remove(Exercise toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExerciseNotFoundException();
        }
        displayedList.setAll(internalUnmodifiableList);

    }

    public void setExercises(ExerciseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        displayedList.setAll(internalUnmodifiableList);
    }

    /**
     * Replaces the contents of this list with {@code exercises}.
     * {@code exercises} can contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        requireAllNonNull(exercises);
        internalList.setAll(exercises);
        displayedList.setAll(internalUnmodifiableList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exercise> asDisplayedList() {
        return displayedList;
    }

    @Override
    public Iterator<Exercise> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseList // instanceof handles nulls
                        && internalList.equals(((ExerciseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code exercises} contains only unique exercises.
     */
    private boolean exercisesAreUnique(List<Exercise> exercises) {
        for (int i = 0; i < exercises.size() - 1; i++) {
            for (int j = i + 1; j < exercises.size(); j++) {
                if (exercises.get(i).isSameExercise(exercises.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the displayedList according to the chronological order of the date field of exercise.
     */
    public void sortDisplayedList() {
        Collections.sort(displayedList);
    }

    /**
     * Resets the displayedList to the default order (internalList).
     */
    public void resetDisplayedList() {
        displayedList.setAll(internalUnmodifiableList);
    }
}
