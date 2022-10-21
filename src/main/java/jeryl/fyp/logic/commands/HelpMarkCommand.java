package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for MARK command for display.
 */
public class HelpMarkCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows MARK Command usage instructions.\n"
            + "Example: " + COMMAND_WORD + " " + MarkCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_MARK_MESSAGE = "MARK Command:\n"
            + "Marks a FYP as \"Done\" OR \"In Progress\" OR \"Yet to Start\", etc.\n"
            + "FORMAT: mark id/STUDENT_ID s/STATUS";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MARK_MESSAGE, false, false);
    }
}
