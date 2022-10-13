package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * AddModuleCommand class represents an AddModuleCommand which adds the module.
 */
public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addmod";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a module to the module list.\n"
            + "parameters: "
            + PREFIX_MOD_CODE + "MODULE_CODE";
    public static final String MODULE_ADDED_SUCCESS = "Module has been added successfully!";

    public static final String DUPLICATE_MODULE_DETECTED = "This module code already exists!";
    private final Module moduleAdded;

    /**
     * Constructor of the AddModuleCommand class which
     * helps to add a module.
     *
     * @param moduleAdded
     */
    public AddModuleCommand(Module moduleAdded) {
        requireNonNull(moduleAdded);
        this.moduleAdded = moduleAdded;

    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(moduleAdded)) {
            throw new CommandException(DUPLICATE_MODULE_DETECTED);
        }
        model.addModule(moduleAdded);
        return new CommandResult(MODULE_ADDED_SUCCESS);
    }

    @Override
    public boolean equals(Object otherAddCommand) {
        return otherAddCommand == this
                || (otherAddCommand instanceof AddModuleCommand
                && moduleAdded.equals(((AddModuleCommand) otherAddCommand).moduleAdded));
    }
}
