package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.listing.exceptions.ListingNotFoundException;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteListingCommand extends Command {

    public static final String COMMAND_WORD = "delL";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the listing identified by the id used in the displayed listings list.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LISTING_SUCCESS = "Deleted Listing: %1$s";

    private final ListingId id;

    public DeleteListingCommand(ListingId id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws ListingNotFoundException {
        requireNonNull(model);
        Listing target = model.getListing(id);

        if (!model.hasListing(target)) {
            throw new ListingNotFoundException(Messages.MESSAGE_INVALID_LISTING_ID);
        }

        model.deleteListing(target);
        model.deleteOffersFor(target);
        model.deleteMeetingsAbout(target);
        return new CommandResult(String.format(MESSAGE_DELETE_LISTING_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteListingCommand // instanceof handles nulls
                && id.equals(((DeleteListingCommand) other).id)); // state check
    }
}

