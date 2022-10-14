package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.offer.Offer;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewListingOffersCommand extends Command {

    public static final String COMMAND_WORD = "listing offers";

    public static final String MESSAGE_SUCCESS = "Listed all listing offers";

    private final String listingId;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ViewListingOffersCommand(String listingId) {
        this.listingId = listingId;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        final StringBuilder builder = new StringBuilder();
        for (Offer offer : model.getListing(listingId).getCurrentOffers()) {
            builder.append(offer).append("\n");
        }
        return new CommandResult(MESSAGE_SUCCESS + "\n" + builder);
    }
}



