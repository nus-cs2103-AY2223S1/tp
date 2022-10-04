package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.guest.model.GuestBook;
import seedu.guest.model.Model;

/**
 * Clears the guest book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Guest book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGuestBook(new GuestBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
