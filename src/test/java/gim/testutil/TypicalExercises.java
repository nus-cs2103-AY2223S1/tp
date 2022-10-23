package gim.testutil;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_DATE_2;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.model.ExerciseTracker;
import gim.model.exercise.Exercise;


/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise ALICE = new ExerciseBuilder().withName("Alice Pauline")
            .withReps("1").withSets("1")
            .withWeight("10")
            .withDate(VALID_DATE).build();
    public static final Exercise BENSON = new ExerciseBuilder().withName("Benson Meier")
            .withReps("1")
            .withSets("1").withWeight("20")
            .withDate(VALID_DATE).build();
    public static final Exercise CARL = new ExerciseBuilder().withName("Carl Kurz").withWeight("30")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise DANIEL = new ExerciseBuilder().withName("Daniel Meier").withWeight("40")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise ELLE = new ExerciseBuilder().withName("Elle Meyer").withWeight("50")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise FIONA = new ExerciseBuilder().withName("Fiona Kunz").withWeight("60")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise GEORGE = new ExerciseBuilder().withName("George Best").withWeight("70")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();

    // Manually added
    public static final Exercise HOON = new ExerciseBuilder().withName("Hoon Meier").withWeight("80")
            .withSets("1").withReps("1").build();
    public static final Exercise IDA = new ExerciseBuilder().withName("Ida Mueller").withWeight("90")
            .withSets("1").withReps("1").build();

    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final Exercise ARM_CURLS = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withReps(VALID_REPS_ARM_CURLS).withDate(VALID_DATE).build();
    public static final Exercise ARM_CURLS_2 = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withReps(VALID_REPS_ARM_CURLS).withDate(VALID_DATE_2).build();
    public static final Exercise BENCH_PRESS = new ExerciseBuilder()
            .withName(VALID_NAME_BENCH_PRESS).withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS)
            .withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code ExerciseTracker} with all the typical exercises.
     */
    public static ExerciseTracker getTypicalExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getTypicalExercises()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted same exercises but different dates.
     */
    public static ExerciseTracker getUnsortedSameExercisesDifferentDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getUnsortedSameExercisesDifferentDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getUnsortedSameExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS_2, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted same exercises but different dates.
     */
    public static ExerciseTracker getSortedSameExercisesDifferentDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getSortedSameExercisesDifferentDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getSortedSameExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, ARM_CURLS_2));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted different exercises but same dates.
     */
    public static ExerciseTracker getUnsortedDifferentExercisesSameDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getUnsortedDifferentExercisesSameDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getUnsortedDifferentExercisesSameDates() {
        return new ArrayList<>(Arrays.asList(BENCH_PRESS, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted different exercises but same dates.
     */
    public static ExerciseTracker getSortedDifferentExercisesSameDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getSortedDifferentExercisesSameDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getSortedDifferentExercisesSameDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, BENCH_PRESS));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted different exercises and different dates.
     */
    public static ExerciseTracker getUnsortedDifferentExercisesDifferentDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getUnsortedDifferentExercisesDifferentDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getUnsortedDifferentExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS_2, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted different exercises and different dates.
     */
    public static ExerciseTracker getSortedDifferentExercisesDifferentDatesExerciseTracker() {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (Exercise exercise : getSortedDifferentExercisesDifferentDates()) {
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

    public static List<Exercise> getSortedDifferentExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, ARM_CURLS_2));
    }
}
