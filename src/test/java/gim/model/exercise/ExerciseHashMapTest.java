package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.BENSON;
import static gim.testutil.TypicalExercises.CARL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.testutil.ExerciseBuilder;


public class ExerciseHashMapTest {

    private final ExerciseHashMap exerciseHashMap = new ExerciseHashMap();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.contains(null));
    }

    @Test
    public void contains_exerciseNotInHashMap_returnsFalse() {
        assertFalse(exerciseHashMap.contains(ALICE));
    }

    @Test
    public void contains_exerciseInHashMap_returnsTrue() {
        exerciseHashMap.add(ALICE);
        assertTrue(exerciseHashMap.contains(ALICE));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInHashMap_returnsTrue() {
        exerciseHashMap.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseHashMap.contains(editedAlice));
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
        assertThrows(ExerciseNotFoundException.class, () -> exerciseHashMap.remove(ALICE));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        exerciseHashMap.add(ALICE);
        exerciseHashMap.remove(ALICE);
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
        expectedArrayList.add("Alice Pauline");
        expectedArrayList.add("Benson Meier");
        expectedArrayList.add("Carl Kurz");
        exerciseHashMap.add(BENSON);
        exerciseHashMap.add(CARL);
        exerciseHashMap.add(ALICE);
        assertEquals(expectedArrayList, exerciseHashMap.getAllKeys());
    }

    @Test
    public void getAllKeys_nonEmptyHashMap_returnsSortedArrayListKeysWithAddRemove() {
        ArrayList<String> expectedArrayList = new ArrayList<>();
        expectedArrayList.add("Alice Pauline");
        expectedArrayList.add("Benson Meier");
        exerciseHashMap.add(BENSON);
        exerciseHashMap.add(CARL);
        exerciseHashMap.add(ALICE);
        exerciseHashMap.remove(CARL);
        assertEquals(expectedArrayList, exerciseHashMap.getAllKeys());
    }

    @Test
    public void numOfValues_emptyHashMap_returnsZero() {
        assertEquals(0, exerciseHashMap.numOfValues());
    }

    @Test
    public void numOfValues_nonEmptyHashMap() {
        exerciseHashMap.add(BENSON);
        assertEquals(1, exerciseHashMap.numOfValues());
    }

}
