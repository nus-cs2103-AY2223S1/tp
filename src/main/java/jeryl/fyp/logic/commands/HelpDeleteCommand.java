package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for DELETE command for display.
 */
public class HelpDeleteCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows DELETE Command usage instructions.\n"
            + "Example: " + COMMAND_WORD + " " + DeleteCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_DELETE_MESSAGE = "DELETE Command:\n"
            + "Removes a FYP from the FYP manager.\n"
            + "FORMAT: delete id/STUDENT_ID";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_DELETE_MESSAGE, false, false);
    }
}
