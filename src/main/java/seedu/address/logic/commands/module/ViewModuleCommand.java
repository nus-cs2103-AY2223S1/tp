package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

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
 * Shows module details in profNUS to the user.
 */
public class ViewModuleCommand extends Command {

    public static final String COMMAND_WORD = "mview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the module identified by the module code used in the list of modules.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " c/CS2103T ";

    public static final String MESSAGE_SUCCESS = "Module details for %1$s loaded successfully.";

    private final ModuleCode targetModuleCode;

    private boolean isInModuleList = false;

    public ViewModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> moduleList = model.getAllModuleList();
        Module moduleToView;

        int positionInList = 0;

        for (Module m : moduleList) {
            if (m.getCode().equals(targetModuleCode)) {
                isInModuleList = true;
                positionInList = moduleList.indexOf(m);
                break;
            }
        }

        if (!isInModuleList) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Index targetIndex = Index.fromZeroBased(positionInList);
        moduleToView = moduleList.get(targetIndex.getZeroBased());
        model.viewModuleDetails(moduleToView.getCode());
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToView), false, false,
                false, false, true, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((ViewModuleCommand) other).targetModuleCode)); // state check
    }
}
