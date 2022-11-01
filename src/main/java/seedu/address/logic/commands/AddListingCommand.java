package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;

/**
 * Adds a person to the address book.
 */
public class AddListingCommand extends Command {

    public static final String COMMAND_WORD = "addL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a listing to the address book. "
            + "Parameters: "
            + PREFIX_LISTING_ID + "ID "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_NAME + "NAME "
            + PREFIX_ASKING_PRICE + "ASKING PRICE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LISTING_ID + "3412 "
            + PREFIX_ADDRESS + " 100 Charming Avenue "
            + PREFIX_NAME + "Jake Holt "
            + PREFIX_ASKING_PRICE + "1 ";

    public static final String MESSAGE_SUCCESS = "New listing added: %1$s";
    public static final String MESSAGE_DUPLICATE_LISTING = "This listing already exists in the address book";

    private final ListingId id;
    private final Address address;
    private final Name name;
    private final Price askingPrice;
    private Listing toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddListingCommand(ListingId id, Address address, Name name, Price askingPrice) {
        requireAllNonNull(address, name);
        this.id = id;
        this.address = address;
        this.name = name;
        this.askingPrice = askingPrice;
        this.toAdd = new Listing(id, address, name, askingPrice);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        this.toAdd = new Listing(id, address, name, askingPrice);

        if (model.hasListing(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LISTING);
        }

        model.addListing(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddListingCommand // instanceof handles nulls
                && toAdd.getId().equals(((AddListingCommand) other).toAdd.getId()));
    }

}
