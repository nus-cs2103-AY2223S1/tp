package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Reverts the {@code model}'s application book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the previous application book state from the history.\n";

    public static final String MESSAGE_SUCCESS = "Undone successfully!";

    public static final String MESSAGE_NO_PREVIOUS_COMMAND = "No previous commands to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoApplicationBook()) {
            throw new CommandException(MESSAGE_NO_PREVIOUS_COMMAND);
        }

        model.undoApplicationBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
