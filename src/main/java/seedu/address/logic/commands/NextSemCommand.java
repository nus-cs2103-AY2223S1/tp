package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits the details of an existing person or the user in the address book.
 */
public class NextSemCommand extends Command {

    public static final String COMMAND_WORD = "nextsem";

    public static final String MESSAGE_SUCCESS = "A new semester has begun!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shifts users and all contacts current modules into "
            + "previous modules.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.nextSem();
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS, false, false, false);
    }

}
