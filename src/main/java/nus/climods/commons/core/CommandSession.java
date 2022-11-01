package nus.climods.commons.core;

import java.util.ArrayList;
import java.util.List;

import nus.climods.logic.Logic;
import nus.climods.logic.commands.CommandResult;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.storage.exceptions.StorageException;

/**
 * Keeps the state of commands in application
 */
public class CommandSession {

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory;
    private int commandHistoryPos;

    /**
     * Constructor for CommandSession class.
     *
     * @param commandExecutor a function that is responsible for executing commands
     */
    public CommandSession(CommandExecutor commandExecutor) {
        this.commandHistory = new ArrayList<>();
        this.commandExecutor = commandExecutor;
        resetCommandScroller();
    }

    private void resetCommandScroller() {
        commandHistoryPos = commandHistory.size();
    }

    private void addCommand(String command) {
        // Only add to command history if command is different from previous command
        // This is to replicate the behavior on zsh
        if (commandHistory.isEmpty() || !command.equals(commandHistory.get(commandHistory.size() - 1))) {
            this.commandHistory.add(command);
        }
        resetCommandScroller();
    }

    private String getCommand() {
        return (commandHistoryPos >= 0 && commandHistoryPos < commandHistory.size()) ? commandHistory.get(commandHistoryPos) : "";
    }

    /**
     * Get the previous command in the command history.
     * <p>
     * Note that there is a side effect to each call to <code>getPreviousCommand</code>, where the internal
     * <code>commandHistoryPos</code> will be updated to point to the position of the previous command in
     * <code>commandHistory</code>.
     * <p>
     * This means that each function call to <code>getPreviousCommand</code> returns a different value based on the
     * <code>commandHistory</code>, hence it is a impure function.
     * </p>
     *
     * @return previous command in command history
     */
    public String getPreviousCommand() {
        commandHistoryPos = Math.max(commandHistoryPos - 1, -1);
        return getCommand();
    }

    /**
     * Get the next command in the command history.
     * <p>
     * Note that there is a side effect to each call to <code>getNextCommand</code>, where the internal
     * <code>commandHistoryPos</code> will be updated to point to the position of the next command in
     * <code>commandHistory</code>.
     * <p>
     * This means that each function call to <code>getNextCommand</code> returns a different value based on the
     * <code>commandHistory</code>, hence it is a impure function.
     * </p>
     *
     * @return next command in command history
     */
    public String getNextCommand() {
        commandHistoryPos = Math.min(commandHistoryPos + 1, commandHistory.size());
        return getCommand();
    }

    /**
     * Execute the command and returns the result
     *
     * @param commandText command string
     */
    public CommandResult execute(String commandText) throws CommandException, ParseException, StorageException {
        addCommand(commandText);
        return commandExecutor.execute(commandText);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {

        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException, StorageException;
    }
}
