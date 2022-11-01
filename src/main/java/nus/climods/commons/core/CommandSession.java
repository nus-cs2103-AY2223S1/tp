package nus.climods.commons.core;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
    private ListIterator<String> commandScroller;

    /**
     * Constructor for CommandSession class.
     *
     * @param commandExecutor a function that is responsible for executing commands
     */
    public CommandSession(CommandExecutor commandExecutor) {
        this.commandHistory = new ArrayList<>();
        this.commandExecutor = commandExecutor;
        this.commandScroller = commandHistory.listIterator();
    }

    private void resetCommandScroller() {
        commandScroller = commandHistory.listIterator(commandHistory.size());
    }

    private void addCommand(String command) {
        // Only add to command history if command is different from previous command
        // This is to replicate the behavior on zsh
        if (commandHistory.isEmpty() || !command.equals(commandHistory.get(commandHistory.size() - 1))) {
            this.commandHistory.add(command);
        }
        resetCommandScroller();
    }

    /**
     * Get the previous command in the command history.
     * <p>
     * Note that there is a side effect to each call to <code>getPreviousCommand</code>, where the internal
     * <code>commandScroller</code> will be updated to point to the position of the previous command in
     * <code>commandHistory</code>.
     * <p>
     * This means that each function call to <code>getPreviousCommand</code> returns a different value based on the
     * <code>commandHistory</code>, hence it is a impure function.
     * </p>
     *
     * @return previous command in command history
     */
    public String getPreviousCommand() {
        return commandScroller.hasPrevious() ? commandScroller.previous() : "";
    }

    /**
     * Get the next command in the command history.
     * <p>
     * Note that there is a side effect to each call to <code>getNextCommand</code>, where the internal
     * <code>commandScroller</code> will be updated to point to the position of the next command in
     * <code>commandHistory</code>.
     * <p>
     * This means that each function call to <code>getNextCommand</code> returns a different value based on the
     * <code>commandHistory</code>, hence it is a impure function.
     * </p>
     *
     * @return next command in command history
     */
    public String getNextCommand() {
        return commandScroller.hasNext() ? commandScroller.next() : "";
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
