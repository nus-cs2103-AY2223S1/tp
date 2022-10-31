package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * Deletes a module identified using the module code from Plannit.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "delete-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module, identified by the module code, from Plannit.\n"
            + "Parameters: " + PREFIX_MODULE_CODE + "MODULE_CODE (must correspond to existing module)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS1231S";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final ModuleCode targetModuleCode;

    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Module> lastShownList = model.getFilteredModuleList();

        Module moduleToDelete = null;
        try {
            moduleToDelete =
                    model.getModuleUsingModuleCode(targetModuleCode, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(MESSAGE_NO_SUCH_MODULE);
        }
        assert moduleToDelete != null;
        model.deleteModule(moduleToDelete);
        model.goToHomePage();
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }
}
