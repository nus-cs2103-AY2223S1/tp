package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.tag.Tag;

/**
 * Adds a set of Tags to the Listing.
 */
public class AddTagsToListingCommand extends Command {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the listing in the address book. "
            + "Parameters: "
            + PREFIX_TAG + " tags \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + " 3 bedroom \n";

    public static final String MESSAGE_SUCCESS = "New tags added to listing: %1$s";
    public static final String MESSAGE_DUPLICATE_TAGS = "These tags already exists in the listing";

    private final Set<Tag> tags;
    private final ListingId listingId;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTagsToListingCommand(Set<Tag> tags, ListingId listingId) {
        requireNonNull(tags);
        this.tags = tags;
        this.listingId = listingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Listing listing = model.getListing(listingId);

        if (listing.hasTag(tags)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAGS);
        }

        listing.addTags(tags);
        model.setListing(model.getListing(listingId), listing);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tags));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagsToListingCommand // instanceof handles nulls
                && tags.equals(((AddTagsToListingCommand) other).tags));
    }
}
