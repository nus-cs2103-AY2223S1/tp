package seedu.phu.logic.commands;

import seedu.phu.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Internship Book as requested ...";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
