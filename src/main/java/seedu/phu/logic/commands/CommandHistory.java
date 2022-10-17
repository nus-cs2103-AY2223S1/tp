package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the history of commands that have been executed.
 */
public class CommandHistory {
    private List<String> history;
    private List<Integer> modifyCommandIndexes;
    private int currentStatePointer;

    /**
     * Creates a new instance of CommandHistory.
     */
    public CommandHistory() {
        history = new ArrayList<>();
        modifyCommandIndexes = new ArrayList<>();
        modifyCommandIndexes.add(-1);
        currentStatePointer = 0;
    }

    /**
     * Add a command text to CommandHistory.
     *
     * @param commandText a command text to be inserted.
     */
    public void addCommand(String commandText) {
        requireNonNull(commandText);
        history.add(commandText);
    }

    /**
     * Set the last command in CommandHistory as modify, i.e. it's execution
     * modified the internship book.
     */
    public void setLastCommandAsModify() {
        assert history.size() > 0;

        removeModifyCommandAfterCurrentPointer();
        modifyCommandIndexes.add(history.size() - 1);
        currentStatePointer++;
    }

    /**
     * Return the previous command in CommandHistory that has modified the internship book.
     *
     * @return the previous modify command in CommandHistory
     */
    public String getPreviousModifyCommand() {
        assert currentStatePointer > 0;

        String previousModifyCommand = history.get(modifyCommandIndexes.get(currentStatePointer));
        currentStatePointer--;

        return previousModifyCommand;
    }

    /**
     * Returns the next command in CommandHistory that has modified the internship book.
     *
     * @return the next modify command in CommandHistory
     */
    public String getNextModifyCommand() {
        assert currentStatePointer < modifyCommandIndexes.size() - 1;

        currentStatePointer++;
        String nextModifyCommand = history.get(modifyCommandIndexes.get(currentStatePointer));

        return nextModifyCommand;
    }

    private void removeModifyCommandAfterCurrentPointer() {
        int numberOfModifyCommandsToBeRemoved = modifyCommandIndexes.size() - 1 - currentStatePointer;

        for (int i = 0; i < numberOfModifyCommandsToBeRemoved; i++) {
            modifyCommandIndexes.remove(currentStatePointer + 1);
        }
    }
}
