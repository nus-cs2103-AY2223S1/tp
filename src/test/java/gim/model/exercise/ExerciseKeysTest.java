package gim.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gim.model.exercise.exceptions.ExerciseNotFoundException;

public class ExerciseKeysTest {

    @Test
    public void exerciseKeys_nullCreation() {
        assertThrows( ExerciseNotFoundException.class, () -> new ExerciseKeys(null));
    }

    @Test
    public void getDisplay_emptyArrayList_emptyOutput() {
        ExerciseKeys exerciseKeys = new ExerciseKeys(new ArrayList<>());
        assertEquals("You have no stored exercises in the system!", exerciseKeys.getDisplay());
    }

    @Test
    public void getDisplay_oneElemArrayList_listOutput() {
        ArrayList<String> arrL = new ArrayList<>();
        arrL.add("Arm Curl");
        ExerciseKeys exerciseKeys = new ExerciseKeys(arrL);
        assertEquals("Stored exercises:\n1. Arm Curl\n", exerciseKeys.getDisplay());
    }


}
