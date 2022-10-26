package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Locks FinBook.
 */
public class LockCommand extends Command {

    public static final String COMMAND_WORD = "lock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Locks FinBook.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_LOCK_MESSAGE = "FinBook locked.";

    public static final String SHOWING_UNLOCK_MESSAGE = "FinBook unlocked.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LOCK_MESSAGE, false, false, true, 0);
    }
}
