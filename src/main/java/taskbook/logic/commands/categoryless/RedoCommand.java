package taskbook.logic.commands.categoryless;

import static taskbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static taskbook.model.Model.PREDICATE_SHOW_ALL_TASKS;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Reverts to the previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Reverted to the previously undone state.";
    public static final String MESSAGE_INVALID_ACTION = "There are no actions left to redo.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Redoes the previous undo action and reverts the TaskBook to a previously undone state.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canRedoTaskBook()) {
            throw new CommandException(MESSAGE_INVALID_ACTION);
        }

        model.redoTaskBook();
        model.updateFilteredPersonListPredicate(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskListPredicate(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
