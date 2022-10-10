package gim.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import gim.model.ExerciseTracker;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ExerciseTracker} with sample data.
 */
public class SampleDataUtil {
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Alex Yeoh"), new Weight("12.5"), new Sets("1"),
                new Reps("1"),
                getTagSet("friends")),
            new Exercise(new Name("Bernice Yu"), new Weight("60"), new Sets("2"),
                new Reps("2"),
                getTagSet("colleagues", "friends")),
            new Exercise(new Name("Charlotte Oliveiro"), new Weight("16.25"), new Sets("3"),
                new Reps("3"),
                getTagSet("neighbours")),
            new Exercise(new Name("David Li"), new Weight("120"), new Sets("4"),
                new Reps("4"),
                getTagSet("family")),
            new Exercise(new Name("Irfan Ibrahim"), new Weight("100"), new Sets("5"),
                new Reps("5"),
                getTagSet("classmates")),
            new Exercise(new Name("Roy Balakrishnan"), new Weight("120"), new Sets("6"),
                new Reps("1"),
                getTagSet("colleagues"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
