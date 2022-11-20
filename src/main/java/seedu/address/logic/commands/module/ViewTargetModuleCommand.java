package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Views more details about a module identified using it's displayed index from profNus module list view.
 */
public class ViewTargetModuleCommand extends Command {

    public static final String COMMAND_WORD = "vtarget";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display more information about a module in the "
            + " Module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_TARGET_MODULE_SUCCESS = "More info on module: %1$s";

    private final Index targetIndex;

    public ViewTargetModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getAllModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module targetModule = lastShownList.get(targetIndex.getZeroBased());
        ModuleCode targetModuleCode = targetModule.getCode();
        model.viewModule(targetModuleCode);

        return new CommandResult(String.format(MESSAGE_VIEW_TARGET_MODULE_SUCCESS, targetModule),
                false, false, false, false,
                true, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands
                .module.ViewTargetModuleCommand // instanceof handles nulls
                && targetIndex.equals(((seedu.address.logic.commands
                .module.ViewTargetModuleCommand) other).targetIndex)); // state check
    }
}

