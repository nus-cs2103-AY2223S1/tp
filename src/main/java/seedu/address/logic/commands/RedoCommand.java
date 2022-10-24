package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.NextStateNotFoundException;
import seedu.address.model.exceptions.PreviousStateNotFoundException;

/**
 * Returns bobaBot to the state before UndoCommand.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns bobaBot to the state before the UndoCommand.\n"
            + "Parameters: No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Successfully returned to the state before UndoCommand!";

    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.redoAddressBook();
        } catch (PreviousStateNotFoundException | NextStateNotFoundException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(MESSAGE_REDO_SUCCESS, false, false, false, true);
    }
}
