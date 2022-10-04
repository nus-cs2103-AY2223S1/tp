package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guest.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import seedu.guest.model.Model;

/**
 * Lists all guests in the guest book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all guests";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
