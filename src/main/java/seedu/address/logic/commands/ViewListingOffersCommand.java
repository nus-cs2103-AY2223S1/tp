package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.listing.ListingID;
import seedu.address.model.offer.Offer;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewListingOffersCommand extends Command {

    public static final String COMMAND_WORD = "listing offers";

    public static final String MESSAGE_SUCCESS = "Listed all listing offers";

    private final ListingID id;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ViewListingOffersCommand(ListingID id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        final StringBuilder builder = new StringBuilder();
        for (Offer offer : model.getListing(id).getCurrentOffers()) {
            builder.append(offer).append("\n");
        }
        return new CommandResult(MESSAGE_SUCCESS + "\n" + builder);
    }
}



