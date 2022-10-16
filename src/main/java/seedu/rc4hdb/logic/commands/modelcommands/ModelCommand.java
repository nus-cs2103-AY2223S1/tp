package seedu.rc4hdb.logic.commands.modelcommands;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Represents a command that interacts with a {@code Model}.
 */
public interface ModelCommand extends Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(Model model) throws CommandException;

}
