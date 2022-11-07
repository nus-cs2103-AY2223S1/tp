package gim.model;

import gim.model.exercise.Exercise;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an exercise tracker
 */
public interface ReadOnlyExerciseTracker {

    /**
     * Returns an unmodifiable view of the exercises list.
     * This list may contain duplicate exercises.
     */
    ObservableList<Exercise> getExerciseList();

}
