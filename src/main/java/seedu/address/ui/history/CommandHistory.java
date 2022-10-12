package seedu.address.ui.history;

import java.util.ArrayList;

/**
 * The history component that is responsible for storing past commands.
 */
public class CommandHistory {
    private ArrayList<String> previousCommands = new ArrayList<>();
    private int pointer = previousCommands.size();

    /**
     * Adds user input into the storage for command history.
     * @param userInput The command that user entered.
     */
    public void add(String userInput) {
        previousCommands.add(userInput);
        reset();
    }

    /**
     * Resets the pointer to point at last item.
     */
    public void reset() {
        pointer = previousCommands.size();
    }

    /**
     * Moves pointer upwards if possible to return to previous command.
     * @return The previous command.
     */
    public String up() {
        pointer--;
        try {
            return previousCommands.get(pointer);
        } catch (IndexOutOfBoundsException e) {
            pointer++;
            return "";
        }
    }

    /**
     * Moves pointer downwards if possible to return to the next command.
     * @return The next command.
     */
    public String down() {
        pointer++;
        try {
            return previousCommands.get(pointer);
        } catch (IndexOutOfBoundsException e) {
            pointer = previousCommands.size();
            return "";
        }
    }

}
