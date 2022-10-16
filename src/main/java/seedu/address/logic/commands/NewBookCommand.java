package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class NewBookCommand extends Command {

    public static final String COMMAND_WORD = "new";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Creating New Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
