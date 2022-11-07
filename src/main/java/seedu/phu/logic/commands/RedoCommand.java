package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.phu.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;

/**
 * Reverts the {@code model}'s address book to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "The next command that modified the internship book"
            + " has been redone.\nSuccessfully redo the following command, '%s'";

    public static final String MESSAGE_FAILURE = "There is no command to redo!"
            + " The command to be redone need to previously modified the internship book.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoInternshipBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoInternshipBook();
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        String commandToRedo = commandHistory.getNextModifyCommand();

        return new CommandResult(String.format(MESSAGE_SUCCESS, commandToRedo));
    }
}
