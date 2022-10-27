package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ABDUCTION;
import static gim.testutil.TypicalExercises.BENCH_PRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.testutil.ExerciseBuilder;

public class ExerciseListTest {

    private final ExerciseList exerciseList = new ExerciseList();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.contains(null));
    }

    @Test
    public void contains_exerciseNotInList_returnsFalse() {
        assertFalse(exerciseList.contains(ABDUCTION));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        exerciseList.add(ABDUCTION);
        assertTrue(exerciseList.contains(ABDUCTION));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInList_returnsTrue() {
        exerciseList.add(ABDUCTION);
        Exercise editedAlice = new ExerciseBuilder(ABDUCTION).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseList.contains(editedAlice));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.add(null));
    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercise(null, ABDUCTION));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercise(ABDUCTION, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> exerciseList.setExercise(ABDUCTION, ABDUCTION));
    }

    @Test
    public void setExercise_editedExerciseIsSameExercise_success() {
        exerciseList.add(ABDUCTION);
        exerciseList.setExercise(ABDUCTION, ABDUCTION);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(ABDUCTION);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasSameIdentity_success() {
        exerciseList.add(ABDUCTION);
        Exercise editedAbduction = new ExerciseBuilder(ABDUCTION).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        exerciseList.setExercise(ABDUCTION, editedAbduction);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(editedAbduction);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        exerciseList.add(ABDUCTION);
        exerciseList.setExercise(ABDUCTION, BENCH_PRESS);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(BENCH_PRESS);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> exerciseList.remove(ABDUCTION));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        exerciseList.add(ABDUCTION);
        exerciseList.remove(ABDUCTION);
        ExerciseList expectedExerciseList = new ExerciseList();
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercises_nullUniqueExerciseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercises((ExerciseList) null));
    }

    @Test
    public void setExercises_uniqueExerciseList_replacesOwnListWithProvidedUniqueExerciseList() {
        exerciseList.add(ABDUCTION);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(BENCH_PRESS);
        exerciseList.setExercises(expectedExerciseList);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercises_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercises((List<Exercise>) null));
    }

    @Test
    public void setExercises_list_replacesOwnListWithProvidedList() {
        exerciseList.add(ABDUCTION);
        List<Exercise> exerciseList = Collections.singletonList(BENCH_PRESS);
        this.exerciseList.setExercises(exerciseList);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(BENCH_PRESS);
        assertEquals(expectedExerciseList, this.exerciseList);
    }
}
