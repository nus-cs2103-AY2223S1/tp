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
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "mdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the module name used in the list of modules.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2103T ";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted module: %1$s";

    private final ModuleCode targetModuleCode;

    private boolean isInModuleList = false;

    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> moduleList = model.getFilteredModuleList();
        Module moduleToDelete;
        int positionInList = 0;

        for (Module m : moduleList) {
            String targetCodeInUpperCase = targetModuleCode.toString().toUpperCase();
            if (m.getCode().toString().equals(targetCodeInUpperCase)) {
                isInModuleList = true;
                positionInList = moduleList.indexOf(m);
                break;
            }
        }

        if (!isInModuleList) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Index targetIndex = Index.fromZeroBased(positionInList);
        moduleToDelete = moduleList.get(targetIndex.getZeroBased());
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete),
                false, false, true,
                false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }
}
