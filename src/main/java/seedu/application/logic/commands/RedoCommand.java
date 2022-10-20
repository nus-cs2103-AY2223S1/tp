package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Reverts the {@code model}'s application book to its previous state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores a previously undone application book state from its history.\n";

    public static final String MESSAGE_SUCCESS = "Redo successfully!";

    public static final String MESSAGE_NO_PREVIOUSLY_UNDONE = "No previously undone commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoApplicationBook()) {
            throw new CommandException(MESSAGE_NO_PREVIOUSLY_UNDONE);
        }

        model.redoApplicationBook();
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
