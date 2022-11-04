package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
<<<<<<< HEAD
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.listing.exceptions.ListingNotFoundException;
=======
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
>>>>>>> 321dd027d21e60ed86ac890882791583c4015fcb

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteListingCommand extends Command {

    public static final String COMMAND_WORD = "delL";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the listing identified by the id used in the displayed listings list.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LISTING_SUCCESS = "Deleted Listing: %1$s";

    private final Index index;

    public DeleteListingCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws ListingNotFoundException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getFilteredListingList();

<<<<<<< HEAD
        if (!model.hasListing(target)) {
            throw new ListingNotFoundException(Messages.MESSAGE_INVALID_LISTING_ID);
=======
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_ID);
>>>>>>> 321dd027d21e60ed86ac890882791583c4015fcb
        }

        Listing listingToDelete = lastShownList.get(index.getZeroBased());
        model.deleteListing(listingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LISTING_SUCCESS, listingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteListingCommand // instanceof handles nulls
                && index.equals(((DeleteListingCommand) other).index)); // state check
    }
}

