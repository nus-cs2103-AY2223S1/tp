package bookface.logic.commands.delete;

import bookface.logic.commands.Command;

/**
 * Deletes a user identified using it's displayed index from the user list.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an entity from BookFace.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + " Suitable subcommands: \n"
            + "Example: " + COMMAND_WORD + " user" + " 1";
}
