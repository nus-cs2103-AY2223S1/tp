package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.model.Model;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.person.Name;

/**
 * Adds an interested client to the listing.
 */
public class AddInterestedClientCommand extends Command {

    public static final String COMMAND_WORD = "addIC";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interested client to the listing. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LISTING_ID
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_LISTING_ID + "001";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the listing";

    private final Name toAdd;
    private final ListingId listingId;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddInterestedClientCommand(Name client, ListingId listingId) {
        requireNonNull(client);
        toAdd = client;
        this.listingId = listingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Listing listing = model.getListing(listingId);
        if (listing.hasInterestedClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        listing.addInterestedClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterestedClientCommand // instanceof handles nulls
                && toAdd.equals(((AddInterestedClientCommand) other).toAdd));
    }
}
