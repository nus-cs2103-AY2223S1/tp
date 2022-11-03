package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.actions.UiAction.UI_HELP;

import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "> Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, UI_HELP);
    }
}
