package seedu.boba.logic.commands;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param bobaBotModel {@code BobaBotModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(BobaBotModel bobaBotModel) throws CommandException, ParseException;

}
