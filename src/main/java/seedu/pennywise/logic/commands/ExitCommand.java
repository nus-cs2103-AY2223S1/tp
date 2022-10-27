package seedu.pennywise.logic.commands;

import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;

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
