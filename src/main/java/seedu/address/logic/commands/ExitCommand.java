package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Survin as requested ...";
    public static final String MESSAGE_USAGE = "Exits survin.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
