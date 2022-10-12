package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.BENCH_PRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

//import gim.model.exercise.exceptions.DuplicateExerciseException;
import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.testutil.ExerciseBuilder;

public class UniqueExerciseListTest {

    private final UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.contains(null));
    }

    @Test
    public void contains_exerciseNotInList_returnsFalse() {
        assertFalse(uniqueExerciseList.contains(ALICE));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        uniqueExerciseList.add(ALICE);
        assertTrue(uniqueExerciseList.contains(ALICE));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueExerciseList.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDates(VALID_DATE)
                .build();
        assertTrue(uniqueExerciseList.contains(editedAlice));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.add(null));
    }

    //    @Test
    //    public void add_duplicateExercise_throwsDuplicateExerciseException() {
    //        uniqueExerciseList.add(ALICE);
    //        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.add(ALICE));
    //    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(null, ALICE));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(ALICE, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.setExercise(ALICE, ALICE));
    }

    @Test
    public void setExercise_editedExerciseIsSameExercise_success() {
        uniqueExerciseList.add(ALICE);
        uniqueExerciseList.setExercise(ALICE, ALICE);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(ALICE);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasSameIdentity_success() {
        uniqueExerciseList.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDates(VALID_DATE)
                .build();
        uniqueExerciseList.setExercise(ALICE, editedAlice);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(editedAlice);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        uniqueExerciseList.add(ALICE);
        uniqueExerciseList.setExercise(ALICE, BENCH_PRESS);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH_PRESS);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    //    @Test
    //    public void setExercise_editedExerciseHasNonUniqueIdentity_throwsDuplicateExerciseException() {
    //        uniqueExerciseList.add(ALICE);
    //        uniqueExerciseList.add(BENCH_PRESS);
    //        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.setExercise(ALICE, BENCH_PRESS));
    //    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.remove(ALICE));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        uniqueExerciseList.add(ALICE);
        uniqueExerciseList.remove(ALICE);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullUniqueExerciseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((UniqueExerciseList) null));
    }

    @Test
    public void setExercises_uniqueExerciseList_replacesOwnListWithProvidedUniqueExerciseList() {
        uniqueExerciseList.add(ALICE);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH_PRESS);
        uniqueExerciseList.setExercises(expectedUniqueExerciseList);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((List<Exercise>) null));
    }

    @Test
    public void setExercises_list_replacesOwnListWithProvidedList() {
        uniqueExerciseList.add(ALICE);
        List<Exercise> exerciseList = Collections.singletonList(BENCH_PRESS);
        uniqueExerciseList.setExercises(exerciseList);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH_PRESS);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    //    @Test
    //    public void setExercises_listWithDuplicateExercises_throwsDuplicateExerciseException() {
    //        List<Exercise> listWithDuplicateExercises = Arrays.asList(ALICE, ALICE);
    //        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList
    //                .setExercises(listWithDuplicateExercises));
    //    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueExerciseList.asUnmodifiableObservableList()
                .remove(0));
    }
}
