package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ABDUCTION;
import static gim.testutil.TypicalExercises.BICEP_CURLS;
import static gim.testutil.TypicalExercises.CALF_RAISES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.testutil.ExerciseBuilder;


public class ExerciseHashMapTest {

    private final ExerciseHashMap exerciseHashMap = new ExerciseHashMap();
    private final ExerciseHashMap exerciseHashMapWithObserverList = new ExerciseHashMap(new ArrayList<>());

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.contains(null));
    }

    @Test
    public void contains_exerciseNotInHashMap_returnsFalse() {
        assertFalse(exerciseHashMap.contains(ABDUCTION));
    }

    @Test
    public void contains_exerciseInHashMap_returnsTrue() {
        exerciseHashMap.add(ABDUCTION);
        assertTrue(exerciseHashMap.contains(ABDUCTION));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInHashMap_returnsTrue() {
        exerciseHashMap.add(ABDUCTION);
        Exercise editedAbduction = new ExerciseBuilder(ABDUCTION).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseHashMap.contains(editedAbduction));
    }

    @Test
    public void initialize_withNullArrayList_throwsAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> new ExerciseHashMap(null));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.add(null));
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> exerciseHashMap.remove(ABDUCTION));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        exerciseHashMap.add(ABDUCTION);
        exerciseHashMap.remove(ABDUCTION);
        ExerciseHashMap expectedExerciseHashMap = new ExerciseHashMap();
        assertEquals(expectedExerciseHashMap, exerciseHashMap);
    }

    @Test
    public void getAllKeys_emptyHashMap_returnsEmptyArrayListKeys() {
        ArrayList<String> expectedArrayList = new ArrayList<>();
        assertEquals(expectedArrayList, exerciseHashMap.getAllKeys());
    }

    @Test
    public void getAllKeys_nonEmptyHashMap_returnsSortedArrayListKeysWithAdd() {
        ArrayList<String> expectedArrayList = new ArrayList<>();
        expectedArrayList.add("ABDUCTION");
        expectedArrayList.add("BICEP CURLS");
        expectedArrayList.add("CALF RAISES");
        exerciseHashMap.add(BICEP_CURLS);
        exerciseHashMap.add(CALF_RAISES);
        exerciseHashMap.add(ABDUCTION);
        assertEquals(expectedArrayList, exerciseHashMap.getAllKeys());
    }

    @Test
    public void getAllKeys_nonEmptyHashMap_returnsSortedArrayListKeysWithAddRemove() {
        ArrayList<String> expectedArrayList = new ArrayList<>();
        expectedArrayList.add("ABDUCTION");
        expectedArrayList.add("BICEP CURLS");
        exerciseHashMap.add(BICEP_CURLS);
        exerciseHashMap.add(CALF_RAISES);
        exerciseHashMap.add(ABDUCTION);
        exerciseHashMap.remove(CALF_RAISES);
        assertEquals(expectedArrayList, exerciseHashMap.getAllKeys());
    }

    @Test
    public void numOfValues_emptyHashMap_returnsZero() {
        assertEquals(0, exerciseHashMap.numOfValues());
    }

    @Test
    public void numOfValues_nonEmptyHashMap() {
        exerciseHashMap.add(BICEP_CURLS);
        assertEquals(1, exerciseHashMap.numOfValues());
    }

}
