package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteListingCommand extends Command {

    public static final String COMMAND_WORD = "delete listing";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the listing identified by the id used in the displayed listings list.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LISTING_SUCCESS = "Deleted Listing: %1$s";

    private final String id;

    public DeleteListingCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Listing target = model.getListing(id);

        if (!model.hasListing(target)) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_ID);
        }

        model.deleteListing(target);
        return new CommandResult(String.format(MESSAGE_DELETE_LISTING_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteListingCommand // instanceof handles nulls
                && id.equals(((DeleteListingCommand) other).id)); // state check
    }
}

