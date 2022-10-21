package tuthub.ui;

import tuthub.logic.commands.CommandResult;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.exceptions.ParseException;

/**
 * Represents a function that can execute commands.
 */
@FunctionalInterface
public interface CommandExecutor {
    /**
     * Executes the command and returns the result.
     *
     * @see tuthub.logic.Logic#execute(String)
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;
}
