package seedu.pennyWise.logic.commands;

import seedu.pennyWise.model.GraphConfiguration;
import seedu.pennyWise.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting PennyWise as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, GraphConfiguration.ofDefault());
    }

}
