package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for ADD command for display.
 */
public class HelpAddCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + " " + AddCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_ADD_MESSAGE = "ADD Command:\n"
            + "Adds a new FYP of a student to the FYP manager.\n"
            + "FORMAT: add id/STUDENT_ID n/STUDENT_NAME proj/FYP_NAME e/EMAIL [t/TAG]…";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_ADD_MESSAGE, false, false);
    }
}
