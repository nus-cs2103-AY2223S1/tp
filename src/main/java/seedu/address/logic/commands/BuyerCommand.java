package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.role.Buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a buyer to the address book.
 */
public class BuyerCommand extends Command {

    public static final String COMMAND_WORD = "buyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Updates a buyer role to the address book. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PRICE_RANGE + " PRICE RANGE "
            + PREFIX_CHARACTERISTICS + " CHARACTERISTICS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PRICE_RANGE + " 200000 - 250000 "
            + PREFIX_CHARACTERISTICS + " 5-ROOM; SOUTH-FACING; BISHAN ";

    public static final String MESSAGE_SUCCESS = "Buyer added: %1$s";

    private final Name contactName;
    private final Buyer buyer;

    /**
     * Creates a Buyer Command to add the specified {@code Buyer} 
     * to the {@code Person} with specified {@code Name}
     */
    public BuyerCommand(Buyer buyer, Name contactName) {
        requireAllNonNull(buyer, contactName);
        this.buyer = buyer;
        this.contactName = contactName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.setContactRole(contactName, buyer);
        return new CommandResult(String.format(MESSAGE_SUCCESS, buyer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerCommand // instanceof handles nulls
                && buyer.equals(((BuyerCommand) other).buyer));
    }
}
