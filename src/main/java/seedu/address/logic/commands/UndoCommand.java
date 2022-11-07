package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Restores survin to a previous state.
 *
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the previous command. \n" + "Example: "
            + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Undo is successful!";
    public static final String MESSAGE_CANNOT_UNDO = "No states to undo to!";

    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoSurvin()) {
            throw new CommandException(MESSAGE_CANNOT_UNDO);
        }

        model.undoSurvin();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
