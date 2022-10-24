package gim.model.exercise;

import java.util.ArrayList;

/**
 * A class that contains the names of Keys in the Exercise Hashmap.
 */
public class ExerciseKeys {

    private ArrayList<String> keyArrayList;

    /**
     * Creates a {@code ExerciseKeys}.
     */
    public ExerciseKeys(ArrayList<String> arrL) {
        assert arrL != null;
        keyArrayList = arrL;
    }

    /**
     * Returns the formatted display for UI SavedExerciseListWindow based on the current state of the Hashmap.
     * @return String
     */
    public String getDisplay() {
        if (keyArrayList.size() == 0) {
            return "You have no registered exercise in the system.";
        }
        StringBuilder sb = new StringBuilder("Unique registered exercise(s):\n");
        for (int i = 1; i < keyArrayList.size() + 1; i++) {
            sb.append(i);
            sb.append(". ");
            sb.append(keyArrayList.get(i - 1));
            sb.append("\n");
        }
        sb.append("You have " + keyArrayList.size() + " unique exercise(s) registered with the system.\n");
        return sb.toString();
    }

}
