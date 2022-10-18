package nus.climods.logic.commands;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.predicate.ViewModulePredicate;

/**
 * View details for a module
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details for a module.\n"
            + "Parameters: MODULE-CODE [LESSON-TYPE...]\n"
            + "Example: " + COMMAND_WORD + " " + "CS2103";
    public static final String MESSAGE_MODULE_NOT_FOUND = "'%s' not in current NUS curriculum";
    public static final String MESSAGE_SUCCESS = "Viewing details for module %s";
    private final String moduleCode;

    public ViewCommand(String moduleCode) {
        this.moduleCode = moduleCode.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasListModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, moduleCode));
        }
        model.setFilteredModuleList(new ViewModulePredicate(moduleCode));
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode));
    }
}
