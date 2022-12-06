package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.JeeqTracker;
import seedu.address.model.Model;

/**
 * Clears all entries in the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all entries in JeeqTracker.\n"
            + "Example: clear";

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setJeeqTracker(new JeeqTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
