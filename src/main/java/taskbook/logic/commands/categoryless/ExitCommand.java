package taskbook.logic.commands.categoryless;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Task Book as requested ...";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Ends the application.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
