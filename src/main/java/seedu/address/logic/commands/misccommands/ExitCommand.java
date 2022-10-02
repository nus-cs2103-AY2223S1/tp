package seedu.address.logic.commands.misccommands;

import seedu.address.logic.commands.CommandResult;

/**
 * Terminates the program.
 */
public class ExitCommand extends MiscCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
