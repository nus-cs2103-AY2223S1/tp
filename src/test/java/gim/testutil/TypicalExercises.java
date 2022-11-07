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
import static gim.testutil.ExerciseTrackerBuilder.listToExerciseTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.model.ExerciseTracker;
import gim.model.exercise.Exercise;


/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise ABDUCTION = new ExerciseBuilder().withName("ABDUCTION")
            .withReps("1").withSets("1")
            .withWeight("10")
            .withDate(VALID_DATE).build();
    public static final Exercise BICEP_CURLS = new ExerciseBuilder().withName("BICEP CURLS")
            .withReps("1")
            .withSets("1").withWeight("20")
            .withDate(VALID_DATE).build();
    public static final Exercise CALF_RAISES = new ExerciseBuilder().withName("CALF RAISES").withWeight("30")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise DEADLIFT = new ExerciseBuilder().withName("DEADLIFT").withWeight("40")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise ELEVATED_SQUATS = new ExerciseBuilder().withName("ELEVATED SQUATS").withWeight("50")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise FRONT_SQUATS = new ExerciseBuilder().withName("FRONT SQUATS").withWeight("60")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();
    public static final Exercise GLUTE_RAISES = new ExerciseBuilder().withName("GLUTE RAISES").withWeight("70")
            .withSets("1").withReps("1").withDate(VALID_DATE).build();

    // Manually added
    public static final Exercise HIP_THRUSTS = new ExerciseBuilder().withName("HIP THRUSTS").withWeight("80")
            .withSets("1").withReps("1").build();
    public static final Exercise INCHWORM = new ExerciseBuilder().withName("INCHWORM").withWeight("90")
            .withSets("1").withReps("1").build();

    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final Exercise ARM_CURLS = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withReps(VALID_REPS_ARM_CURLS).withDate(VALID_DATE).build();
    public static final Exercise ARM_CURLS_2 = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withReps(VALID_REPS_ARM_CURLS).withDate(VALID_DATE_2).build();
    public static final Exercise ARM_CURLS_WITHOUT_DATE = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withReps(VALID_REPS_ARM_CURLS).build();
    public static final Exercise BENCH_PRESS = new ExerciseBuilder()
            .withName(VALID_NAME_BENCH_PRESS).withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS)
            .withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
            .build();

    // Manually added (for PrCommand Testing)
    public static final Exercise SQUAT_LIGHT = new ExerciseBuilder().withName("Squat").withWeight("50")
            .withSets("1").withReps("1").build();
    public static final Exercise SQUAT_HEAVY = new ExerciseBuilder().withName("Squat").withWeight("100")
            .withSets("1").withReps("1").build();
    public static final Exercise DEADLIFT_LIGHT = new ExerciseBuilder().withName("Deadlift").withWeight("70")
            .withSets("1").withReps("1").build();
    public static final Exercise DEADLIFT_HEAVY = new ExerciseBuilder().withName("Deadlift").withWeight("120")
            .withSets("1").withReps("1").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code ExerciseTracker} with all the typical exercises.
     */
    public static ExerciseTracker getTypicalExerciseTracker() {
        return listToExerciseTracker(getTypicalExercises());
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(ABDUCTION, BICEP_CURLS, CALF_RAISES, DEADLIFT,
                ELEVATED_SQUATS, FRONT_SQUATS, GLUTE_RAISES));
    }

    /**
     * Returns an {@code ExerciseTracker} with no exercises.
     */
    public static ExerciseTracker getNoExerciseExerciseTracker() {
        return listToExerciseTracker(getNoExercise());
    }

    public static List<Exercise> getNoExercise() {
        return new ArrayList<>();
    }

    /**
     * Returns an {@code ExerciseTracker} with one exercise.
     */
    public static ExerciseTracker getOneExerciseExerciseTracker() {
        return listToExerciseTracker(getOneExercise());
    }

    public static List<Exercise> getOneExercise() {
        return new ArrayList<>(List.of(SQUAT_LIGHT));
    }

    /**
     * Returns an {@code ExerciseTracker} with two exercises with the same Name but different Weights.
     */
    public static ExerciseTracker getSameExerciseNameDifferentWeightsExerciseTracker() {
        return listToExerciseTracker(getSameExerciseDifferentWeights());
    }

    public static List<Exercise> getSameExerciseDifferentWeights() {
        return new ArrayList<>(Arrays.asList(SQUAT_LIGHT, SQUAT_HEAVY));
    }

    /**
     * Returns an {@code ExerciseTracker} with two exercises with the different Names and different Weights.
     */
    public static ExerciseTracker getTwoDifferentExercisesExerciseTracker() {
        return listToExerciseTracker(getTwoDifferentExercises());
    }

    public static List<Exercise> getTwoDifferentExercises() {
        return new ArrayList<>(Arrays.asList(SQUAT_LIGHT, DEADLIFT_LIGHT));
    }

    /**
     * Returns an {@code ExerciseTracker} with two exercises with the different Names and different Weights.
     */
    public static ExerciseTracker twoDifferentExercisesTwoDifferentWeightsExerciseTracker() {
        return listToExerciseTracker(twoDifferentExercisesTwoDifferentWeights());
    }

    public static List<Exercise> twoDifferentExercisesTwoDifferentWeights() {
        return new ArrayList<>(Arrays.asList(SQUAT_LIGHT, SQUAT_HEAVY, DEADLIFT_LIGHT, DEADLIFT_HEAVY));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted same exercises but different dates.
     */
    public static ExerciseTracker getUnsortedSameExercisesDifferentDatesExerciseTracker() {
        return listToExerciseTracker(getUnsortedSameExercisesDifferentDates());
    }

    public static List<Exercise> getUnsortedSameExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS_2, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted same exercises but different dates.
     */
    public static ExerciseTracker getSortedSameExercisesDifferentDatesExerciseTracker() {
        return listToExerciseTracker(getSortedSameExercisesDifferentDates());
    }

    public static List<Exercise> getSortedSameExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, ARM_CURLS_2));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted different exercises but same dates.
     */
    public static ExerciseTracker getUnsortedDifferentExercisesSameDatesExerciseTracker() {
        return listToExerciseTracker(getUnsortedDifferentExercisesSameDates());
    }

    public static List<Exercise> getUnsortedDifferentExercisesSameDates() {
        return new ArrayList<>(Arrays.asList(BENCH_PRESS, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted different exercises but same dates.
     */
    public static ExerciseTracker getSortedDifferentExercisesSameDatesExerciseTracker() {
        return listToExerciseTracker(getSortedDifferentExercisesSameDates());
    }

    public static List<Exercise> getSortedDifferentExercisesSameDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, BENCH_PRESS));
    }

    /**
     * Returns an {@code ExerciseTracker} with unsorted different exercises and different dates.
     */
    public static ExerciseTracker getUnsortedDifferentExercisesDifferentDatesExerciseTracker() {
        return listToExerciseTracker(getUnsortedDifferentExercisesDifferentDates());
    }

    public static List<Exercise> getUnsortedDifferentExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS_2, ARM_CURLS));
    }

    /**
     * Returns an {@code ExerciseTracker} with sorted different exercises and different dates.
     */
    public static ExerciseTracker getSortedDifferentExercisesDifferentDatesExerciseTracker() {
        return listToExerciseTracker(getSortedDifferentExercisesDifferentDates());
    }

    public static List<Exercise> getSortedDifferentExercisesDifferentDates() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS, ARM_CURLS_2));
    }

    /**
     * Returns an {@code ExerciseTracker} with a sample unsorted exercises.
     */
    public static ExerciseTracker getSampleUnsortedExercisesExerciseTracker() {
        return listToExerciseTracker(getSampleUnsortedExercises());
    }

    public static List<Exercise> getSampleUnsortedExercises() {
        return new ArrayList<>(Arrays.asList(ARM_CURLS_2, ARM_CURLS, BENCH_PRESS));
    }
}
