package gim.logic.commands;

import gim.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = ":wq";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Exercise Tracker as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
