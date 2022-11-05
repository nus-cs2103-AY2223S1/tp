package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import tracko.commons.core.Messages;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;
import tracko.model.order.OrderDateTimeComparator;

/**
 * Sorts the displayed list of orders by its timeCreated based on the argument keyword.
 * Keyword matching is case insensitive.
 */
public class SortOrdersCommand extends Command {

    public static final String COMMAND_WORD = "sorto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all orders based on the time and date "
            + "displays them as a list with index numbers.\n"
            + "Parameters: 'new' or 'old' \n"
            + "Example: " + COMMAND_WORD + " new";

    private final OrderDateTimeComparator comparator;

    public SortOrdersCommand(OrderDateTimeComparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedOrderList(comparator);

        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_SORTED_OVERVIEW, model.getSortedOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortOrdersCommand // instanceof handles nulls
                && comparator.equals(((SortOrdersCommand) other).comparator)); // state check
    }
}
