package bookface.logic.commands;

import bookface.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting BookFace as requested ...";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Exits the program",
            COMMAND_WORD);

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
