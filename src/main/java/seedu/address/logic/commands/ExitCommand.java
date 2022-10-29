package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting NUScheduler as requested ...";
    public static final String MESSAGE_HELP = "Closes and exits NUScheduler.";
    public static final String MESSAGE_FAILURE = "Please remove extra inputs after exit to exit NUScheduler.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
