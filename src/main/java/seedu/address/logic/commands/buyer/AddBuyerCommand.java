package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;

/**
 * Adds a buyer to the address book.
 */
public class AddBuyerCommand extends Command {

    public static final String COMMAND_WORD = "addbuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a buyer to Cobb.\n"
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PHONE + " PHONE "
            + PREFIX_EMAIL + " EMAIL "
            + PREFIX_ADDRESS + " ADDRESS "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE]"
            + "[" + PREFIX_CHARACTERISTICS + " DESIRED CHARACTERISTICS] "
            + "[" + PREFIX_PRIORITY + " PRIORITY <HIGH/NORMAL/LOW>]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " john@gmail.com "
            + PREFIX_ADDRESS + " 311 Clementi Ave 2, #02-25"
            + PREFIX_PRICE_RANGE + " 600000 - 800000 "
            + PREFIX_CHARACTERISTICS + " 5-ROOM; SOUTH-FACING; "
            + PREFIX_PRIORITY + " high ";

    public static final String MESSAGE_SUCCESS = "New buyer added!\n%1$s";
    public static final String MESSAGE_DUPLICATE_BUYER = "This buyer already exists in Cobb";

    private final Buyer toAdd;

    /**
     * Creates an AddBuyerCommand to add the specified {@code Buyer}
     */
    public AddBuyerCommand(Buyer buyer) {
        requireNonNull(buyer);
        toAdd = buyer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBuyer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BUYER);
        }

        model.addBuyer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBuyerCommand // instanceof handles nulls
                && toAdd.equals(((AddBuyerCommand) other).toAdd));
    }
}
