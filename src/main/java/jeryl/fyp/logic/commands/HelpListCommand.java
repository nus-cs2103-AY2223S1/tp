package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for LIST command for display.
 */
public class HelpListCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows FIND Command usage instructions.\n"
            + "Example: " + COMMAND_WORD + " " + ListCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_LIST_MESSAGE = "LIST Command:\n"
            + "Shows a list of final year projects with the student IDs.\n"
            + "FORMAT: list";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_LIST_MESSAGE, false, false);
    }
}
