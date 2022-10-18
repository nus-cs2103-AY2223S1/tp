package nus.climods.logic.commands;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;

/**
 * View details for a module
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details for a module.\n"
            + "Parameters: MODULE-CODE [LESSON-TYPE...]\n"
            + "Example: " + COMMAND_WORD + " " + "CS2103";
    private final String moduleCode;

    public ViewCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
