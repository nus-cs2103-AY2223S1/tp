package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * AddModuleCommand class represents an AddModuleCommand which adds the module.
 */
public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "m " + COMMAND_WORD + ": adds a module to the module list.\n"
            + "Parameters: "
            + PREFIX_MOD_CODE + "MODULE_CODE "
            + PREFIX_MOD_NAME + "MODULE_NAME "
            + PREFIX_MOD_CREDIT + "MODULAR_CREDIT\n"
            + "Example: " + "m " + COMMAND_WORD + " " + PREFIX_MOD_CODE + "CS2100 "
            + PREFIX_MOD_NAME + "Computer organisation " + PREFIX_MOD_CREDIT + "4";
    public static final String MODULE_ADDED_SUCCESS = "Module has been added successfully!";

    public static final String DUPLICATE_MODULE_DETECTED = "This module already exists! "
            + "Try to input a different module code along with your initial module name and module credit fields.";
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
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MODULE_ADDED_SUCCESS);
    }

    @Override
    public boolean equals(Object otherAddCommand) {
        return otherAddCommand == this
                || (otherAddCommand instanceof AddModuleCommand
                && moduleAdded.equals(((AddModuleCommand) otherAddCommand).moduleAdded));
    }
}
