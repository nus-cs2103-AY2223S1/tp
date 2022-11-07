package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Comparator;
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
     * Returns the displayed list {@code ObservableList}.
     */
    public ObservableList<Exercise> asDisplayedList() {
        return displayedList;
    }

    /**
     * Returns a duplicated displayed list {@code ObservableList}.
     */
    public ObservableList<Exercise> asDuplicatedDisplayedList() {
        ObservableList<Exercise> duplicatedDisplayedList = FXCollections.observableArrayList(displayedList);
        return duplicatedDisplayedList;
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
     * Sorts the displayedList according to the chronological order of the date field of exercise.
     *
     * Algorithm Description:
     * (1) List is sorted by the comparator in increasing chronological order of date. Then, the list is sorted in
     * decreasing alphabetical order by the comparator.
     * (2) Then the call {@code Collections.reverse(displayedList)} will reverse the list. The resulting list
     * will be sorted from the latest chronological date (at the top of the UI) to the oldest chronological
     * date (at the bottom of the UI). For exercises with the same date in the resulting list, they are sorted
     * by increasing alphabetical order.
     */
    public void sortDisplayedList() {
        Comparator<Exercise> comparator = Comparator.comparing(e -> e.getDate().date);
        comparator = comparator.thenComparing((e1, e2) -> e2.getName().fullName.compareTo(e1.getName().fullName));
        Collections.sort(displayedList, comparator);
        Collections.reverse(displayedList);
    }

    /**
     * Resets the displayedList to the default order (internalUnmodifiableList).
     */
    public void resetDisplayedList() {
        displayedList.setAll(internalUnmodifiableList);
    }

    /**
     * Filters the displayedList based on the filtered list that user filtered.
     */
    public void filterDisplayedList(ObservableList<Exercise> filteredList) {
        displayedList.setAll(filteredList);
    }
}
