package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;

/**
 * Reverts the {@code model}'s  WorkBook to its previous state.
 */
public class UndoCommand extends Command {

    /** Command word to execute the undo command */
    public static final String COMMAND_WORD = "undo";

    /** Message string displaying successful execution of the undo command */
    public static final String MESSAGE_SUCCESS = "Undo previous changes successful!";

    /**
     * Message string displaying error message for unsuccessful execution of the undo command
     * for an unmodified WorkBook
     */
    public static final String MESSAGE_NO_CHANGES = "No previous changes to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoWorkBook()) {
            throw new CommandException(MESSAGE_NO_CHANGES);
        }

        model.undoWorkBook();
        model.updateFilteredInternshipList(Model.PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
