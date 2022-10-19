package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Undo the last undo command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_FAILURE = "No command to redo!";
    public static final String MESSAGE_SUCCESS = "Redone next command!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redo();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
