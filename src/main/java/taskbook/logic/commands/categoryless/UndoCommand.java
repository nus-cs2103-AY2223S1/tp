package taskbook.logic.commands.categoryless;

import static taskbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static taskbook.model.Model.PREDICATE_SHOW_ALL_TASKS;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Reverts to the previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Reverted to the previous state.";
    public static final String MESSAGE_INVALID_ACTION = "There are no actions left to undo.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Undoes the previous command and reverts the TaskBook to the previous state.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canUndoTaskBook()) {
            throw new CommandException(MESSAGE_INVALID_ACTION);
        }

        model.undoTaskBook();
        model.updateFilteredPersonListPredicate(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskListPredicate(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
