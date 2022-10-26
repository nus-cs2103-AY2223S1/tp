package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_DATE_2;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.testutil.TypicalExercises.ABDUCTION;
import static gim.testutil.TypicalExercises.BENCH_PRESS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.testutil.ExerciseBuilder;


public class ExerciseTest {

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(ABDUCTION.isSameExercise(ABDUCTION));

        // null -> returns false
        assertFalse(ABDUCTION.isSameExercise(null));

        // same name, all other attributes different -> returns true
        Exercise editedAbduction =
                new ExerciseBuilder(ABDUCTION).withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS)
                        .withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE).build();

        assertTrue(ABDUCTION.isSameExercise(editedAbduction));

        // different name, all other attributes same -> returns false
        editedAbduction = new ExerciseBuilder(ABDUCTION).withName(VALID_NAME_BENCH_PRESS).build();
        assertFalse(ABDUCTION.isSameExercise(editedAbduction));

        // name differs in case, all other attributes same -> returns true
        Exercise editedBenchPress =
                new ExerciseBuilder(BENCH_PRESS).withName(VALID_NAME_BENCH_PRESS.toLowerCase()).build();
        assertTrue(BENCH_PRESS.isSameExercise(editedBenchPress));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BENCH_PRESS + " ";
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withName(nameWithTrailingSpaces).build();
        assertTrue(BENCH_PRESS.isSameExercise(editedBenchPress));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise aliceCopy = new ExerciseBuilder(ABDUCTION).build();
        assertTrue(ABDUCTION.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ABDUCTION.equals(ABDUCTION));

        // null -> returns false
        assertFalse(ABDUCTION.equals(null));

        // different type -> returns false
        assertFalse(ABDUCTION.equals(5));

        // different exercise -> returns false
        assertFalse(ABDUCTION.equals(BENCH_PRESS));

        // different name -> returns false
        Exercise editedAbduction = new ExerciseBuilder(ABDUCTION).withName(VALID_NAME_BENCH_PRESS).build();
        assertFalse(ABDUCTION.equals(editedAbduction));

        // different weight -> returns false
        editedAbduction = new ExerciseBuilder(ABDUCTION).withWeight(VALID_WEIGHT_BENCH_PRESS).build();
        assertFalse(ABDUCTION.equals(editedAbduction));

        // different sets -> returns false
        editedAbduction = new ExerciseBuilder(ABDUCTION).withSets(VALID_SETS_BENCH_PRESS).build();
        assertFalse(ABDUCTION.equals(editedAbduction));

        // different address -> returns false
        editedAbduction = new ExerciseBuilder(ABDUCTION).withReps(VALID_REPS_BENCH_PRESS).build();
        assertFalse(ABDUCTION.equals(editedAbduction));

        // different tags -> returns false
        editedAbduction = new ExerciseBuilder(ABDUCTION).withDate(VALID_DATE_2).build();
        assertFalse(ABDUCTION.equals(editedAbduction));
    }
}
