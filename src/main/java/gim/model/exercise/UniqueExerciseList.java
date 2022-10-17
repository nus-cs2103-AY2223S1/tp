package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of exercises that enforces uniqueness between its elements and does not allow nulls.
 * A exercise is considered unique by comparing using {@code Exercise#isSameExercise(Exercise)}. As such,
 * adding and updating of exercises uses Exercise#isSameExercise(Exercise) for equality so as to ensure that the
 * exercise being added or updated is unique in terms of identity in the UniqueExerciseList. However, the removal of an
 * exercise uses Exercise#equals(Object) so as to ensure that the exercise with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Exercise#isSameExercise(Exercise)
 */
public class UniqueExerciseList implements Iterable<Exercise> {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exercise> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private HashMap<Name, ArrayList<Exercise>> hashMap;

    public UniqueExerciseList() {
        hashMap = new HashMap<>();
    }

    public HashMap<Name, ArrayList<Exercise>> getHashMap() {
        return hashMap;
    }

    /**
     * Returns true if the list contains an equivalent exercise as the given argument.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return hashMap.containsKey(toCheck.getName());
    }

    /**
     * Adds an exercise to the list.
     * The exercise must not already exist in the list.
     */
    public void add(Exercise toAdd) {
        Name storedName = toAdd.getName();
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            hashMap.put(toAdd.getName(), new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
        } else {
            for (Name key : hashMap.keySet()) { // Store exercise with name of first exercise instance
                if (storedName.equals(key)) {
                    storedName = key;
                    break;
                }
            }
        }
        toAdd = new Exercise(storedName, toAdd.getWeight(), toAdd.getSets(), toAdd.getReps(), toAdd.getDate());
        hashMap.get(storedName).add(toAdd); // add Exercise to arraylist
        internalList.add(toAdd);
    }

    /**
     * Replaces the exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the list.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in the list.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
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
        hashMap.get(toRemove.getName()).remove(toRemove);
        if (hashMap.get(toRemove.getName()).isEmpty()) { // Remove Exercise from hashmap
            hashMap.remove(toRemove.getName()); // If no more Exercises in key's ArrayList, delete key
        }
    }

    public void setExercises(UniqueExerciseList replacement) {
        requireNonNull(replacement);
        hashMap = replacement.getHashMap();
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        requireAllNonNull(exercises);
        for (Exercise e : exercises) {
            Name storedName = e.getName();
            if (!contains(e)) {
                hashMap.put(e.getName(), new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
            } else {
                for (Name key : hashMap.keySet()) { // Store exercise with name of first exercise instance
                    if (storedName.equals(key)) {
                        storedName = key;
                        break;
                    }
                }
            }
            e = new Exercise(storedName, e.getWeight(), e.getSets(), e.getReps(), e.getDate());
            hashMap.get(storedName).add(e); // add Exercise to arraylist
        }
        internalList.setAll(exercises);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exercise> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Exercise> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueExerciseList // instanceof handles nulls
                        && internalList.equals(((UniqueExerciseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
