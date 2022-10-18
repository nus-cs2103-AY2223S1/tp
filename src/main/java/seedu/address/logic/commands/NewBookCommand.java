package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Creates a new Address Book.
 */
public class NewBookCommand extends Command {

    public static final String COMMAND_WORD = "new";

    public static final String MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT = "Creating New Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT, null,
                false, true, false, false);
    }

}
