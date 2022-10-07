package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to Plannit.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "add-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to Plannit."
            + "Parameters: "
            + PREFIX_MODULE + "MODULE_CODE "
            + "[" + PREFIX_DESCRIPTION + "MODULE_TITLE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS1231S "
            + PREFIX_DESCRIPTION + "Discrete Structures";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Oops, it looks like you have already "
            + "added the module";

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
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
