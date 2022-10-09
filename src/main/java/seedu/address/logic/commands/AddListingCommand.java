package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a person to the address book.
 */
public class AddListingCommand extends Command {

	public static final String COMMAND_WORD = "addlist";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a listing to the address book. "
			+ "Parameters: "
			+ PREFIX_ADDRESS + "ADRESS "
			+ PREFIX_NAME + "NAME "
			+ PREFIX_ASKING_PRICE + "ASKING PRICE "
			+ "Example: " + COMMAND_WORD + " "
			+ PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
			+ PREFIX_NAME + "Bob "
			+ PREFIX_ASKING_PRICE + "500000";

	public static final String MESSAGE_SUCCESS = "New listing added: %1$s";
	public static final String MESSAGE_DUPLICATE_LISTING = "This listing already exists in the address book";

	private final Listing toAdd;

	/**
	 * Creates an AddCommand to add the specified {@code Person}
	 */
	public AddListingCommand(Listing listing) {
		requireNonNull(listing);
		toAdd = listing;
	}

	@Override
	public CommandResult execute(Model model) throws CommandException {
		requireNonNull(model);

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
				&& toAdd.equals(((AddListingCommand) other).toAdd));
	}
}
