package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the FYP manager";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting FYP Manager as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false);
    }

}
