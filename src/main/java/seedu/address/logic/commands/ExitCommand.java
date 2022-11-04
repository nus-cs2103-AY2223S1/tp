package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("exit");

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting HealthContact as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
