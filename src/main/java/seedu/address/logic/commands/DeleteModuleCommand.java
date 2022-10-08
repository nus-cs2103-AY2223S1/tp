package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes a module identified using the module code from Plannit.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "delete-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module, identified by the module code, from Plannit.\n"
            + "Parameters: MODULE_CODE (must correspond to existing module)\n"
            + "Example: " + COMMAND_WORD + " CS1231S";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final ModuleCode targetModuleCode;

    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Module> lastShownList = model.getFilteredModuleList();

        boolean isFound = false;
        Module moduleToDelete = null;
        for (Module module : lastShownList) {
            if (module.isSameModule(new Module(targetModuleCode))) {
                model.deleteModule(module);
                moduleToDelete = module;
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }
}
