package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static tracko.logic.parser.CliSyntax.*;

import tracko.commons.core.Messages;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;
import tracko.model.order.OrderContainsKeywordsPredicate;
import tracko.model.order.OrderDeliveredOrPaidPredicate;

/**
 * Finds and lists all orders whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all orders that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers."
            + "Keywords under the same prefix are separated by a semicolon(;). "
            + "Use at least one prefix during searching. \n"
            + "Parameters: \n"
            + PREFIX_ITEM + "ITEM_KEYWORD; [MORE_ITEM_KEYWORDS]...\n"
            + PREFIX_ADDRESS + "ADDRESS_KEYWORD; [MORE_ADDRESS_KEYWORDS]...\n"
            + PREFIX_NAME + "NAME_KEYWORD; [MORE_NAME_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_ITEM + "keychain " + PREFIX_ADDRESS + "Ang Mo Kio; Clementi" ;

    private final OrderContainsKeywordsPredicate prefixPredicate;
    private final OrderDeliveredOrPaidPredicate flagPredicate;

    public FindOrderCommand(OrderContainsKeywordsPredicate prefixPredicate,
                            OrderDeliveredOrPaidPredicate flagPredicate) {
        this.prefixPredicate = prefixPredicate;
        this.flagPredicate = flagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(prefixPredicate);
        model.updateFilteredOrderList(flagPredicate);
        model.refreshData();

        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_FOUND_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderCommand // instanceof handles nulls
                && (prefixPredicate.equals(((FindOrderCommand) other).prefixPredicate))
                && flagPredicate.equals(((FindOrderCommand) other).flagPredicate)); // state check
    }
}
