package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.watson.model.Database;
import seedu.watson.model.Model;

/**
 * Clears the watson book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new Database());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
