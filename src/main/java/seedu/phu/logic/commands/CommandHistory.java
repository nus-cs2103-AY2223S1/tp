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
     * Creates a copy of CommandHistory.
     *
     * @param commandHistory an instance to be copied.
     */
    public CommandHistory(CommandHistory commandHistory) {
        history = new ArrayList<>();
        history.addAll(commandHistory.history);
        modifyCommandIndexes = new ArrayList<>();
        modifyCommandIndexes.addAll(commandHistory.modifyCommandIndexes);
        currentStatePointer = commandHistory.currentStatePointer;
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

    protected List<String> getHistory() {
        return history;
    }

    protected List<Integer> getModifyCommandIndexes() {
        return modifyCommandIndexes;
    }

    protected int getCurrentStatePointer() {
        return currentStatePointer;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandHistory)) {
            return false;
        }

        // state check
        CommandHistory otherCommandHistory = (CommandHistory) other;
        return history.equals(otherCommandHistory.history)
                && modifyCommandIndexes.equals(otherCommandHistory.modifyCommandIndexes)
                && currentStatePointer == otherCommandHistory.currentStatePointer;
    }
}
