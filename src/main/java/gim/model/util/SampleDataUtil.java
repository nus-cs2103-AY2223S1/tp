package gim.model.util;

import gim.model.ExerciseTracker;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.date.Date;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;

/**
 * Contains utility methods for populating {@code ExerciseTracker} with sample data.
 */
public class SampleDataUtil {
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Squat"), new Weight("65"), new Sets("1"),
                new Reps("1"),
                new Date("14/10/2022")),
            new Exercise(new Name("Squat"), new Weight("60"), new Sets("1"),
                new Reps("1"),
                new Date("13/10/2022")),
            new Exercise(new Name("Bench Press"), new Weight("40"), new Sets("1"),
                new Reps("1"),
                new Date("13/10/2022")),
            new Exercise(new Name("Deadlift"), new Weight("70"), new Sets("1"),
                new Reps("1"),
                new Date("13/10/2022")),
        };
    }

    public static ReadOnlyExerciseTracker getSampleExerciseTracker() {
        ExerciseTracker sampleAb = new ExerciseTracker();
        for (Exercise sampleExercise : getSampleExercises()) {
            sampleAb.addExercise(sampleExercise);
        }
        return sampleAb;
    }

    /**
     * Returns a date containing the string given.
     */
    public static Date getDate(String string) {
        return new Date(string);
    }

}
