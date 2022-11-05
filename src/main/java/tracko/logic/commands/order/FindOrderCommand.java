package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static tracko.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_NAME;

import tracko.commons.core.Messages;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;
import tracko.model.order.OrderMatchesFlagsAndPrefixPredicate;

/**
 * Finds and lists all orders whose delivery status, payment status, name,
 * item ordered or address matches any of the given parameters.
 * Keyword matching is case-insensitive.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds order(s) that contain any of "
            + "the specified keywords (case-insensitive) "
            + "or match the payment/delivery status denoted by the input flags "
            + "and displays them in the order list.\n"
            + "Keywords under the same prefix are separated by a space.\n"
            + "Use at least one prefix during searching if no flags are used.\n"
            + "Parameters (flags): -d / p or -D / P\n"
            + "Parameters (prefixes): \n"
            + PREFIX_ITEM + "ITEM_NAME_KEYWORD [MORE_ITEM_NAME_KEYWORDS]...\n"
            + PREFIX_ADDRESS + "ADDRESS_KEYWORD [MORE_ADDRESS_KEYWORDS]...\n"
            + PREFIX_NAME + "NAME_KEYWORD [MORE_NAME_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD
            + " -p " + PREFIX_ITEM + "keychain " + PREFIX_ADDRESS + "Ang Mo Kio Clementi";

    public static final String INVALID_FLAG_MESSAGE = "Please use either -d / p or -D / P";

    private final OrderMatchesFlagsAndPrefixPredicate predicate;

    public FindOrderCommand(OrderMatchesFlagsAndPrefixPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_FOUND_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderCommand // instanceof handles nulls
                && predicate.equals(((FindOrderCommand) other).predicate)); // state check
    }
}
