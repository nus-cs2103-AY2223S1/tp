package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gim.model.exercise.exceptions.ExerciseNotFoundException;

/**
 * An Exercise HashMap to categorise Exercises, with the same Name, together. For instance, if a user adds an
 * Exercise with Name.toString() == 'Squat' and another Exercise with Name.toString() == 'squat', they will be put
 * into the exerciseHashMap under the same key 'Squat', where 'Squat' is a Name object.
 *
 * Two Names are equal if, after removal of whitespaces and being set to lowercase, their String values are equal.
 */
public class ExerciseHashMap {

    private final HashMap<Name, ArrayList<Exercise>> exerciseHashMap;

    public ExerciseHashMap() {
        exerciseHashMap = new HashMap<>();
    }

    /**
     * Returns true if the Exercise in the given argument has a Name equal to a Name in the exerciseHashMap key-set.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return exerciseHashMap.containsKey(toCheck.getName());
    }

    /**
     * Adds an Exercise to the exerciseHashMap.
     * If the Exercise already exists, i.e. two Exercises with the same Name, categorise them together. Returns the
     * Exercise added to the exerciseHashMap.
     */
    public Exercise add(Exercise toAdd) {
        requireNonNull(toAdd);
        Name storedName = toAdd.getName();
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
        //        for (Name name: exerciseHashMap.keySet()) {
        //            String key = name.toString();
        //            String value = exerciseHashMap.get(name).toString();
        //            System.out.println(key + " " + value);
        //        }
        return toAdd;
    }

    /**
     * Removes the equivalent Exercise from the exerciseHashMap.
     * The Exercise must exist in the exerciseHashMap.
     */
    public void remove(Exercise toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
            throw new ExerciseNotFoundException();
        } else {
            exerciseHashMap.get(toRemove.getName()).remove(toRemove);
            if (exerciseHashMap.get(toRemove.getName()).isEmpty()) { // Remove Exercise from hashmap
                exerciseHashMap.remove(toRemove.getName()); // If no more Exercises in key's ArrayList, delete key
            }
        }
    }

    //    public void setExercises(ExerciseList replacement) {
    //        requireNonNull(replacement);
    //        exerciseHashMap = replacement.getHashMap();
    //    }

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
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseHashMap // instanceof handles nulls
                && exerciseHashMap.equals(((ExerciseHashMap) other).exerciseHashMap));
    }

}
