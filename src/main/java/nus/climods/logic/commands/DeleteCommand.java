package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.UserModule;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "rm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + " <Module Code> : Deletes the Module as indicated by the user. ";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    public static final String MESSAGE_DELETE_MODULE_FAILED = "Module does not exist in your modules: %1$s";

    private final String targetCode;

    /**
     * Creates a DeleteCommand with the given UserModule
     * @param target UserModule to delete
     */
    public DeleteCommand(String targetCode) {
        requireNonNull(targetCode);
        this.targetCode = targetCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        UserModule toDelete = new UserModule(targetCode, model);

        if (!model.filteredListhasUserModule(toDelete)) {
            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_FAILED, targetCode));
        }

        model.deleteUserModule(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, targetCode));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && targetCode.equals(((DeleteCommand) other).targetCode)); // state check
    }
}
