package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;

/**
 * Sort and list all reviews in the address book to the user.
 */
public class RSortCommand extends Command {

    public static final String COMMAND_WORD = "rsort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the review list by name.\n";

    public static final String MESSAGE_SUCCESS = "The review list is now sorted by name";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.sortReviews();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
