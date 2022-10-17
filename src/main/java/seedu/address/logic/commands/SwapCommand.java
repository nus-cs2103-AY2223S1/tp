package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class SwapCommand extends Command {

    public static final String COMMAND_WORD = "swap";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Swapping Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true, false);
    }

}
