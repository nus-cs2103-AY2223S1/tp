package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to profNus.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "madd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to profNus. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_MODULE_DESCRIPTION + "MODULE DESCRIPTION "
            + PREFIX_TAG + "MODULE TAG\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Software Engineering "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_MODULE_DESCRIPTION + "Teach software engineering principles "
            + PREFIX_TAG + "ModuleCoordinator";


    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "The module already exists in profNus. ";

    private final Module toAdd;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false, false, true,
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
