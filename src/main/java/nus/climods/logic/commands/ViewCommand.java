package nus.climods.logic.commands;

import java.util.Optional;

import org.openapitools.client.ApiException;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.Model;
import nus.climods.model.module.Module;

/**
 * View details for a module
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details for a module.\n"
            + "Parameters: MODULE-CODE [LESSON-TYPE...]\n"
            + "Example: " + COMMAND_WORD + " " + "CS2103";
    public static final String MESSAGE_MODULE_NOT_FOUND = ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE;
    public static final String MESSAGE_API_ERROR = "Error retrieving module details";
    public static final String MESSAGE_SUCCESS = "Viewing details for module %s";

    private final String moduleCode;

    public ViewCommand(String moduleCode) {
        this.moduleCode = moduleCode.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Module> module = model.getListModule(moduleCode);

        if (module.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, moduleCode));
        }

        try {
            model.setModuleInFocus(module.get());
        } catch (ApiException e) {
            throw new CommandException(MESSAGE_API_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode.toUpperCase()), COMMAND_WORD);
    }
}
