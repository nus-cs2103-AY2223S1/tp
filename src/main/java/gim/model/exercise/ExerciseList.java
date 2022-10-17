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
 * A list of exercises that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 */
public class ExerciseList implements Iterable<Exercise> {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exercise> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * An Exercise HashMap to categorise Exercises, with the same Name, together. For instance, if a user adds an
     * Exercise with Name.toString() == 'Squat' and another Exercise with Name.toString() == 'squat', they will be put
     * into the exerciseHashMap under the same key 'Squat', where 'Squat' is a Name object.
     *
     * Two Names are equal if, after removal of whitespaces and being set to lowercase, their String values are equal.
     */
    private HashMap<Name, ArrayList<Exercise>> exerciseHashMap;

    public ExerciseList() {
        exerciseHashMap = new HashMap<>();
    }

    public HashMap<Name, ArrayList<Exercise>> getHashMap() {
        return exerciseHashMap;
    }

    /**
     * Returns true if the Exercise in the given argument has a Name equal to a Name in the exerciseHashMap key-set.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return exerciseHashMap.containsKey(toCheck.getName());
    }

    /**
     * Adds an Exercise to the List and exerciseHashMap.
     * If the Exercise already exists, i.e. two Exercises with the same Name, categorise them together.
     */
    public void add(Exercise toAdd) {
        Name storedName = toAdd.getName();
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            exerciseHashMap.put(toAdd.getName(), new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
        } else {
            for (Name key : exerciseHashMap.keySet()) { // Store exercise with name of first exercise instance
                if (storedName.equals(key)) {
                    storedName = key;
                    break;
                }
            }
        }
        toAdd = new Exercise(storedName, toAdd.getWeight(), toAdd.getSets(), toAdd.getReps(), toAdd.getDate());
        exerciseHashMap.get(storedName).add(toAdd); // add Exercise to arraylist
        internalList.add(toAdd);
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
     * Removes the equivalent Exercise from the List and exerciseHashMap.
     * The Exercise must exist in both the List and the exerciseHashMap.
     */
    public void remove(Exercise toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove) || !exerciseHashMap.get(toRemove.getName()).remove(toRemove)) {
            throw new ExerciseNotFoundException();
        }
        if (exerciseHashMap.get(toRemove.getName()).isEmpty()) { // Remove Exercise from hashmap
            exerciseHashMap.remove(toRemove.getName()); // If no more Exercises in key's ArrayList, delete key
        }
    }

    public void setExercises(ExerciseList replacement) {
        requireNonNull(replacement);
        exerciseHashMap = replacement.getHashMap();
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this List and exerciseHashMap with {@code exercises}.
     */
    public void setExercises(List<Exercise> exercises) {
        requireAllNonNull(exercises);
        //        exerciseHashMap = new HashMap<>();
        for (Exercise e : exercises) {
            Name storedName = e.getName();
            if (!contains(e)) {
                exerciseHashMap.put(e.getName(), new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
            } else {
                for (Name key : exerciseHashMap.keySet()) { // Store exercise with name of first exercise instance
                    if (storedName.equals(key)) {
                        storedName = key;
                        break;
                    }
                }
            }
            e = new Exercise(storedName, e.getWeight(), e.getSets(), e.getReps(), e.getDate());
            exerciseHashMap.get(storedName).add(e); // add Exercise to arraylist
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
                || (other instanceof ExerciseList // instanceof handles nulls
                        && internalList.equals(((ExerciseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
