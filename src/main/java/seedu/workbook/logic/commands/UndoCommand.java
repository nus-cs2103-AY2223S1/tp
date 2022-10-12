package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;

/**
 * Reverts the {@code model}'s address book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_UNDO_SUCCESS = "Undo previous changes successful!";
    public static final String MESSAGE_FAILURE = "No previous changes to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoWorkBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoWorkBook();
        model.updateFilteredInternshipList(Model.PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
