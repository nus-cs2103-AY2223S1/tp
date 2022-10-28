package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Deletes a module identified using it's displayed index from the module list.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = "m " + COMMAND_WORD
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX\n"
            + "Example: m " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final Index targetIndex;

    public DeleteModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_MODULE_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        Module moduleToDelete = lastShownList.get(targetIndex.getZeroBased());

        boolean areTasksOrExamsDeleted = false;

        if (model.hasTaskWithModule(moduleToDelete)) {
            model.deleteTasksWithModule(moduleToDelete);
            areTasksOrExamsDeleted = true;
        }
        if (model.hasExamWithModule(moduleToDelete)) {
            model.deleteExamsWithModule(moduleToDelete);
            areTasksOrExamsDeleted = true;
        }

        model.deleteModule(moduleToDelete);

        if (areTasksOrExamsDeleted) {
            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete) + "\n"
                    + "Warning! All the tasks and exams related to this module have been deleted.");
        }
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleCommand) other).targetIndex)); // state check
    }
}
