package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
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
        assertFalse(exerciseList.contains(ALICE));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        exerciseList.add(ALICE);
        assertTrue(exerciseList.contains(ALICE));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInList_returnsTrue() {
        exerciseList.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseList.contains(editedAlice));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.add(null));
    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercise(null, ALICE));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercise(ALICE, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> exerciseList.setExercise(ALICE, ALICE));
    }

    @Test
    public void setExercise_editedExerciseIsSameExercise_success() {
        exerciseList.add(ALICE);
        exerciseList.setExercise(ALICE, ALICE);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(ALICE);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasSameIdentity_success() {
        exerciseList.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        exerciseList.setExercise(ALICE, editedAlice);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(editedAlice);
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        exerciseList.add(ALICE);
        exerciseList.setExercise(ALICE, BENCH_PRESS);
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
        assertThrows(ExerciseNotFoundException.class, () -> exerciseList.remove(ALICE));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        exerciseList.add(ALICE);
        exerciseList.remove(ALICE);
        ExerciseList expectedExerciseList = new ExerciseList();
        assertEquals(expectedExerciseList, exerciseList);
    }

    @Test
    public void setExercises_nullUniqueExerciseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.setExercises((ExerciseList) null));
    }

    @Test
    public void setExercises_uniqueExerciseList_replacesOwnListWithProvidedUniqueExerciseList() {
        exerciseList.add(ALICE);
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
        exerciseList.add(ALICE);
        List<Exercise> exerciseList = Collections.singletonList(BENCH_PRESS);
        this.exerciseList.setExercises(exerciseList);
        ExerciseList expectedExerciseList = new ExerciseList();
        expectedExerciseList.add(BENCH_PRESS);
        assertEquals(expectedExerciseList, this.exerciseList);
    }

    //    @Test
    //    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
    //        assertThrows(UnsupportedOperationException.class, () -> exerciseList.asDisplayedList()
    //                .remove(0));
    //    }
}
