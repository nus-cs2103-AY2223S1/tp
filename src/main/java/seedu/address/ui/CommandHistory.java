package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A Class to store the Command History of a Command Box.
 */
public class CommandHistory {
    private final List<String> previousCommands;
    private int pointer;

    /**
     * Creates a new CommandHistory object with a new list of commands.
     */
    public CommandHistory() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new CommandHistory object with a pre-existing list of commands.
     * @param previousCommands List of commands.
     */
    public CommandHistory(List<String> previousCommands) {
        this.previousCommands = previousCommands;
        setPointerToEnd();
    }

    /**
     * Add a new command to the history.
     * @param command to be added.
     */
    public void addCommand(String command) {
        previousCommands.add(command);
        setPointerToEnd();
    }

    public Optional<String> getCommand() {
        if (pointer <= -1) {
            return Optional.empty();
        }
        if (pointer == previousCommands.size()) {
            return Optional.empty();
        }
        return Optional.of(previousCommands.get(pointer));
    }

    /**
     * Move the pointer back 1 command in the history.
     */
    public void previousCommand() {
        pointer = (pointer - 1) < 0 ? pointer : (pointer - 1);
    }

    /**
     * Move the pointer forward 1 command in the history.
     */
    public void nextCommand() {
        pointer = (pointer + 1) > previousCommands.size() ? pointer : (pointer + 1);
    }

    private void setPointerToEnd() {
        pointer = previousCommands.size();
    }
}
