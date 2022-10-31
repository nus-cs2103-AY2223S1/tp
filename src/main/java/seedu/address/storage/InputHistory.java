package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

//@@author {gowribhat}-reused
//Reused from https://github.com/AY2122S2-CS2103T-W13-3/tp
/**
 * Represents a storage for the input history of the user.
 */
public class InputHistory {
    private static final int MAX_CAPACITY = 50;
    private List<String> inputList = new ArrayList<>();
    private int index = 0;

    /**
     * Creates a new InputHistory to store inputs.
     */
    public InputHistory() {
        inputList.add("");
    }

    /**
     * Add user input to InputHistory.
     * If max capacity of storage is reached, the oldest input get deleted to store new one.
     *
     * @param input input from the user
     */
    public void add(String input) {
        if (inputList.size() < MAX_CAPACITY) {
            index = inputList.size() - 1;
            inputList.add(index, input);
            index++;
        } else {
            inputList.remove(0);
            inputList.add(input);
        }
    }

    /**
     * Returns users input from InputHistory at current index
     *
     * @return user input at current index
     */
    public String get() {
        return inputList.get(index);
    }

    /**
     * Decrements index when index is more than zero.
     */
    public void up() {
        if (index > 0) {
            index--;
        }
    }

    /**
     * Increments index when index less than maximum index on the input list.
     */
    public void down() {
        if (index < inputList.size() - 1) {
            index++;
        }
    }
}
//@@author
