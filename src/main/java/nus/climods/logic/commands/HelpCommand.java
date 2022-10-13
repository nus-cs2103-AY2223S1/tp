package nus.climods.logic.commands;

import static nus.climods.commons.core.Messages.MESSAGE_SHOW_HELP;

import nus.climods.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
        + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SHOW_HELP, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof HelpCommand;
    }
}
