package gim.testutil;

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
}
