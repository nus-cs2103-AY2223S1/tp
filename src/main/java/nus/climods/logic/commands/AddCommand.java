package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;
import static nus.climods.model.module.UserModule.MESSAGE_MODULE_NOT_FOUND;

import java.util.Optional;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.Module;
import nus.climods.model.module.UserModule;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <Module Code> : Adds a module to module record. ";
    /*
        + "Parameters: "
        + CliSyntax.PREFIX_CODE + "CODE "
        + CliSyntax.PREFIX_TUTORIAL + "TUTORIAL "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_CODE + "CS2103 "
        + CliSyntax.PREFIX_TUTORIAL + "Monday 2pm "
        + CliSyntax.PREFIX_TAG + "4 MC";
     */

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in your list of modules";

    private final String toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(String moduleCode) {
        requireNonNull(moduleCode);
        toAdd = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Module> module = model.getModule(toAdd.toUpperCase());
        if (module.isEmpty()) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        UserModule moduleToAdd = new UserModule(module.get());
        if (model.hasUserModule(moduleToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
        model.addUserModule(moduleToAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toUpperCase()),
                COMMAND_WORD, model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
