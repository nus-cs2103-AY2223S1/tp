package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.NextStateNotFoundException;
import seedu.address.model.exceptions.PreviousStateNotFoundException;

/**
 * Returns bobaBot to the state before executing the previous command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns bobaBot to the state before executing the previous command.\n"
            + "Parameters: No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Successfully returned to previous state!";

    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.undoAddressBook();
        } catch (PreviousStateNotFoundException | NextStateNotFoundException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(MESSAGE_UNDO_SUCCESS, false, false, true, false);
    }
}
