package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;

/**
 * Restores the previously undone modification to CLInkedIn.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Restores the previously undone modification "
            + "to CLInkedIn.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Redo successful!";

    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoAddressBook()) {
            throw new CommandException(Messages.MESSAGE_CANNOT_REDO);
        }
        model.redoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
