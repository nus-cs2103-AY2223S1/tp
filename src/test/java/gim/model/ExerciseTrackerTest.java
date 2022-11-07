package gim.model;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ABDUCTION;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import gim.model.exercise.Exercise;
import gim.testutil.ExerciseBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExerciseTrackerTest {

    private final ExerciseTracker exerciseTracker = new ExerciseTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), exerciseTracker.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExerciseTracker_replacesData() {
        ExerciseTracker newData = getTypicalExerciseTracker();
        exerciseTracker.resetData(newData);
        assertEquals(newData, exerciseTracker);
    }

    @Test
    public void hasExercise_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseTracker.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInExerciseTracker_returnsFalse() {
        assertFalse(exerciseTracker.hasExercise(ABDUCTION));
    }

    @Test
    public void hasExercise_exerciseInExerciseTracker_returnsTrue() {
        exerciseTracker.addExercise(ABDUCTION);
        assertTrue(exerciseTracker.hasExercise(ABDUCTION));
    }

    @Test
    public void hasExercise_exerciseWithSameIdentityFieldsInExerciseTracker_returnsTrue() {
        exerciseTracker.addExercise(ABDUCTION);
        Exercise editedAlice = new ExerciseBuilder(ABDUCTION).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseTracker.hasExercise(editedAlice));
    }

    //    @Test
    //    public void getExerciseList_modifyList_throwsUnsupportedOperationException() {
    //        assertThrows(UnsupportedOperationException.class, () -> exerciseTracker.getExerciseList().remove(0));
    //    }

    /**
     * A stub ReadOnlyExerciseTracker whose exercises list can violate interface constraints.
     */
    private static class ExerciseTrackerStub implements ReadOnlyExerciseTracker {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();

        ExerciseTrackerStub(Collection<Exercise> exercises) {
            this.exercises.setAll(exercises);
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }
    }

}
