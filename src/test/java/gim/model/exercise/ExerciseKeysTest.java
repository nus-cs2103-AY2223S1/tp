package gim.model.exercise;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseKeysTest {

    @Test
    public void getDisplay_EmptyArrayList_emptyOutput() {
        ExerciseKeys exerciseKeys = new ExerciseKeys(new ArrayList<>());
        assertEquals("You have no stored exercises in the system!", exerciseKeys.getDisplay());
    }

    @Test
    public void getDisplay_OneElemArrayList_listOutput() {
        ArrayList<String> arrL = new ArrayList<>();
        arrL.add("Arm Curl");
        ExerciseKeys exerciseKeys = new ExerciseKeys(arrL);
        assertEquals("Stored exercises:\n1. Arm Curl\n",exerciseKeys.getDisplay());
    }


}
