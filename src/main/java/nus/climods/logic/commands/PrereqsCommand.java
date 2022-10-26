package nus.climods.logic.commands;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;

public class PrereqsCommand extends Command {
    public static final String COMMAND_WORD = "deps";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "<Module Code>: List prerequisites for a module.\n"
            + "Example: " + COMMAND_WORD + " " + "CS2103";

    private final String moduleCode;

    public PrereqsCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Prerequisites for: " + moduleCode, false, false);
    }
}
