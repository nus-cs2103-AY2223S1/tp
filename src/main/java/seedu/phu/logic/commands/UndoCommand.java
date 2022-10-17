package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.phu.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;

/**
 * Reverts the {@code model}'s internship book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "The last command that modified the internship book"
            + " has been undone!\nSuccessfully undo the following command, '%s'";

    public static final String MESSAGE_FAILURE = "There is no command to undo!"
            + " The command to be undone need to previously modified the internship book.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoInternshipBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoInternshipBook();
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        String commandToUndo = commandHistory.getPreviousModifyCommand();

        return new CommandResult(String.format(MESSAGE_SUCCESS, commandToUndo));
    }
}
