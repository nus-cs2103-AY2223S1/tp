package seedu.workbook.logic.commands;

import seedu.workbook.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /** Command word to execute the exit command */
    public static final String COMMAND_WORD = "exit";

    /** Message string displaying successful execution of the exit command */
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting WorkBook as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
