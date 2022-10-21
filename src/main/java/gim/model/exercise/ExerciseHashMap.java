package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.ui.Observer;

/**
 * An Exercise HashMap to categorise Exercises, with the same Name, together. For instance, if a user adds an
 * Exercise with Name.toString() == 'Squat' and another Exercise with Name.toString() == 'squat', they will be put
 * into the exerciseHashMap under the same key 'Squat', where 'Squat' is a Name object.
 *
 * Two Names are equal if, after removal of whitespaces and being set to lowercase, their String values are equal.
 */
public class ExerciseHashMap {

    private final HashMap<Name, ArrayList<Exercise>> exerciseHashMap;

    private final ArrayList<Observer> observerArrayList;

    /**
     * Constructs a {@code ExerciseHashMap}.
     */
    public ExerciseHashMap() {
        exerciseHashMap = new HashMap<>();
        observerArrayList = new ArrayList<Observer>();
    }

    /**
     * Notifies observers who have 'subscribed' whenever there are changes in the state of the ExerciseHashMap
     */
    public void notifyObservers() {
        for (Observer o: observerArrayList) {
            o.update();
        }
    }

    /**
     * Allows an Observer object to 'subscribe' to changes in the state of the ExerciseHashMap.
     * @param o Observer object
     */
    public void addUI(Observer o) {
        observerArrayList.add(o);
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

        Name toStoreName = toAdd.getName();
        if (!contains(toAdd)) {
            exerciseHashMap.put(toAdd.getName(), new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
        } else {
            toStoreName = getHashmapKey(toStoreName);
        }
        toAdd = new Exercise(toStoreName, toAdd.getWeight(), toAdd.getSets(), toAdd.getReps(), toAdd.getDate());
        exerciseHashMap.get(toStoreName).add(toAdd); // add Exercise to arraylist
        this.notifyObservers();
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
            this.notifyObservers();
        }
    }

    /**
     * Replaces the contents of this List and exerciseHashMap with {@code exercises}.
     */
    public void setExercises(List<Exercise> exercises) {
        requireAllNonNull(exercises);
        for (Exercise exercise : exercises) {
            Name toStoreName = exercise.getName();
            if (!contains(exercise)) {
                exerciseHashMap.put(exercise.getName(),
                        new ArrayList<>()); // Initialise key with empty ArrayList<Exercise>
            } else {
                toStoreName = getHashmapKey(toStoreName);
            }
            exercise = new Exercise(toStoreName, exercise.getWeight(), exercise.getSets(),
                    exercise.getReps(), exercise.getDate());
            exerciseHashMap.get(toStoreName).add(exercise); // add Exercise to arraylist
        }
    }

    /**
     * Retrieve Name of first Exercise instance (Key of exerciseHashMap)
     * @param toStoreName Name of current Exercise
     * @return Returns the Name of the first Exercise instance
     */
    public Name getHashmapKey(Name toStoreName) {
        Name returnName = toStoreName;
        for (Name key: exerciseHashMap.keySet()) {
            if (toStoreName.equals(key)) {
                returnName = key;
                break;
            }
        }
        return returnName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseHashMap // instanceof handles nulls
                && exerciseHashMap.equals(((ExerciseHashMap) other).exerciseHashMap));
    }

    @Override
    public int hashCode() {
        return exerciseHashMap.hashCode();
    }


    /**
     * Returns an Alphabetically sorted ArrayList of all key values in ExerciseHashMap
     * @return Returns ArrayList of String
     */
    public ArrayList<String> getAllKeys() {
        Set<Name> keySet = exerciseHashMap.keySet();
        ArrayList<String> toReturn = new ArrayList<>();
        if (keySet.isEmpty()) {
            return toReturn;
        }
        for (Name keyName: keySet) {
            toReturn.add(keyName.toString());
        }
        Collections.sort(toReturn);
        return toReturn;
    }

}
