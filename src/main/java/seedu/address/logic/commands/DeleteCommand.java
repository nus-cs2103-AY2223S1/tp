package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an entity identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODE + "MODE (must be 'client', 'company' or 'transaction')\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODE + "client\n";

}
