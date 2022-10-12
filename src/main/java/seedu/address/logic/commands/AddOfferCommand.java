package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.offer.Offer;

/**
 * Adds an offer to the address book.
 */
public class AddOfferCommand extends Command {
    public static final String COMMAND_WORD = "addoffer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an offer to the address book. "
            + "Parameters: "
            + PREFIX_ADDRESS + "LISTING ADDRESS "
            + PREFIX_NAME + "NAME "
            + PREFIX_OFFER + "OFFER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_NAME + "Bob "
            + PREFIX_OFFER + "600000";

    public static final String MESSAGE_SUCCESS = "New offer added: %1$s";
    public static final String MESSAGE_DUPLICATE_OFFER = "This offer already exists in the list of offers";

    private final Offer toAdd;

    /**
     * Creates an AddOfferCommand to add the specified {@code Person}
     */
    public AddOfferCommand(Offer offer) {
        requireNonNull(offer);
        toAdd = offer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasOffer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_OFFER);
        }

        model.addOffer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOfferCommand // instanceof handles nulls
                && toAdd.equals(((AddOfferCommand) other).toAdd));
    }
}
