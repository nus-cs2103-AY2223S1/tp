package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.UserModule;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <Module Code> : Adds a module to module record. ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in your list of modules";

    private final String toAdd;
    private final SemestersEnum semester;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(String moduleCode, SemestersEnum semester) {
        requireNonNull(moduleCode);
        this.toAdd = moduleCode;
        this.semester = semester;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        UserModule moduleToAdd = new UserModule(toAdd, semester, model);

        if (model.hasUserModule(moduleToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addUserModule(moduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
