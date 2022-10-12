package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_DATE_2;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.BENCH_PRESS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.testutil.ExerciseBuilder;


public class ExerciseTest {

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(ALICE.isSameExercise(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameExercise(null));

        // same name, all other attributes different -> returns true
        Exercise editedAlice =
                new ExerciseBuilder(ALICE).withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS)
                        .withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE).build();

        assertTrue(ALICE.isSameExercise(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withName(VALID_NAME_BENCH_PRESS).build();
        assertFalse(ALICE.isSameExercise(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Exercise editedBenchPress =
                new ExerciseBuilder(BENCH_PRESS).withName(VALID_NAME_BENCH_PRESS.toLowerCase()).build();
        assertFalse(BENCH_PRESS.isSameExercise(editedBenchPress));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BENCH_PRESS + " ";
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withName(nameWithTrailingSpaces).build();
        assertFalse(BENCH_PRESS.isSameExercise(editedBenchPress));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise aliceCopy = new ExerciseBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different exercise -> returns false
        assertFalse(ALICE.equals(BENCH_PRESS));

        // different name -> returns false
        Exercise editedAlice = new ExerciseBuilder(ALICE).withName(VALID_NAME_BENCH_PRESS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different weight -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withWeight(VALID_WEIGHT_BENCH_PRESS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different sets -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withSets(VALID_SETS_BENCH_PRESS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withDate(VALID_DATE_2).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
