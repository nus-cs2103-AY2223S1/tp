package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;

/**
 * Reverts the {@code model}'s WorkBook to its previously undone state.
 */
public class RedoCommand extends Command {

    /** Command word to execute the redo command */
    public static final String COMMAND_WORD = "redo";

    /** Message string displaying successful execution of the redo command */
    public static final String MESSAGE_SUCCESS = "Redo success!";

    /**
     * Message string displaying error message for unsuccessful execution of the redo command
     * for a WorkBook with no commands to redo
     */
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoWorkBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoWorkBook();
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
