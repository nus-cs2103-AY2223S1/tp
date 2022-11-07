package gim.testutil;

import java.util.List;

import gim.model.ExerciseTracker;
import gim.model.exercise.Exercise;


/**
 * A utility class to help with building exerciseTracker objects.
 * Example usage: <br>
 *     {@code ExerciseTracker ab = new ExerciseTrackerBuilder().withExercise("John", "Doe").build();}
 */
public class ExerciseTrackerBuilder {

    private ExerciseTracker exerciseTracker;

    public ExerciseTrackerBuilder() {
        exerciseTracker = new ExerciseTracker();
    }

    public ExerciseTrackerBuilder(ExerciseTracker exerciseTracker) {
        this.exerciseTracker = exerciseTracker;
    }

    /**
     * Adds a new {@code Exercise} to the {@code ExerciseTracker} that we are building.
     */
    public ExerciseTrackerBuilder withExercise(Exercise exercise) {
        exerciseTracker.addExercise(exercise);
        return this;
    }

    public ExerciseTracker build() {
        return exerciseTracker;
    }

    /**
     * Converts a List of Exercises into an ExerciseTracker object containing these Exercises.
     * @param list ArrayList.
     * @return ExerciseTracker with Exercises.
     */
    public static ExerciseTracker listToExerciseTracker(List<Exercise> list) {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : list) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }
}
