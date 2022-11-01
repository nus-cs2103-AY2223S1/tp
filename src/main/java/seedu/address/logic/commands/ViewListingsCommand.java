package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LISTINGS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewListingsCommand extends Command {

    public static final String COMMAND_WORD = "listL";

    public static final String MESSAGE_SUCCESS = "Listed all listings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

