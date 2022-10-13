package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for FIND command for display.
 */
public class HelpFindCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows FIND Command usage instructions.\n"
            + "Example: " + COMMAND_WORD + " " + FindCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_FIND_MESSAGE = "FIND Command:\n"
            + "Finds projects whose names contain any of the given keyword(s).\n"
            + "FORMAT: find KEYWORD/[KEYWORD2/KEYWORD3/…]";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_FIND_MESSAGE, false, false);
    }
}
