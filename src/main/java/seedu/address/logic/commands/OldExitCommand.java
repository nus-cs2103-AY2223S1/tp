package seedu.address.logic.commands;

import seedu.address.model.OldModel;

/**
 * Terminates the program.
 */
public class OldExitCommand extends OldCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting address book as requested ...";

    @Override
    public CommandResult execute(OldModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
