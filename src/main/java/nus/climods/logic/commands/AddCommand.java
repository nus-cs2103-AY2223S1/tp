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
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module not in current NUS curriculum";
    public static final String MESSAGE_MODULE_NOT_OFFERED_IN_SEMESTER = "Module not offered in chosen semester";

    private final String moduleCode;
    private final SemestersEnum semester;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(String moduleCode, SemestersEnum semester) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode.toUpperCase();
        this.semester = semester;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isModuleOffered(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }
        if (!model.isModuleOfferedInSemester(moduleCode, semester)) {
            throw new CommandException(MESSAGE_MODULE_NOT_OFFERED_IN_SEMESTER);
        }

        UserModule moduleToAdd = new UserModule(moduleCode, semester);
        if (model.hasUserModule(moduleToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addUserModule(moduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode.toUpperCase()), COMMAND_WORD, model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && moduleCode.equals(((AddCommand) other).moduleCode));
    }
}
