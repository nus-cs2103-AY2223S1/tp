package seedu.rc4hdb.logic.commands.misccommands;

import seedu.rc4hdb.logic.commands.CommandResult;

/**
 * Terminates the program.
 */
public class ExitCommand implements MiscCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting RC4HDB as requested ...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
