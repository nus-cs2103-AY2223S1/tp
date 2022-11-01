package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Restores the model's previously undone {@code ApplicationBook} state from the history.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores a previously undone application book state from the history.\n";

    public static final String MESSAGE_SUCCESS = "Redone successfully!";

    public static final String MESSAGE_NO_PREVIOUSLY_UNDONE = "No previously undone commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoApplicationBook()) {
            throw new CommandException(MESSAGE_NO_PREVIOUSLY_UNDONE);
        }

        model.redoApplicationBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
