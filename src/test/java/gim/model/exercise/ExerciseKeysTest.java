package gim.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ExerciseKeysTest {

    @Test
    public void exerciseKeys_nullCreation() {
        assertThrows(AssertionError.class, () -> new ExerciseKeys(null));
    }

    @Test
    public void getDisplay_emptyArrayList_emptyOutput() {
        ExerciseKeys exerciseKeys = new ExerciseKeys(new ArrayList<>());
        assertEquals("You have no registered exercise in the system.", exerciseKeys.getDisplay());
    }

    @Test
    public void getDisplay_oneElemArrayList_listOutput() {
        ArrayList<String> arrL = new ArrayList<>();
        arrL.add("Arm Curl");
        ExerciseKeys exerciseKeys = new ExerciseKeys(arrL);
        assertEquals("Unique registered exercise(s):\n"
                        + "1. Arm Curl\nYou have 1 unique exercise(s) registered with the system.\n",
                exerciseKeys.getDisplay());
    }


}
