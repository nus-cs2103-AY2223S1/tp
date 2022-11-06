package seedu.address.logic.commands.filtercommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Filters the attributes of an existing Order in the address book.
 */
public class FilterOrderCommand extends FilterCommand {
    public static final String COMMAND_WORD = "filter-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all Orders with attributes: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/[KEYWORDS] PREFIX/[KEYWORDS] ...\n"
            + "There are three possible attributes to filter: Additional requests, Order status, Price range \n"
            + "For Additional requests, use the prefix 'o_ar' \n"
            + "For Order status, use the prefix 'o_st' \n"
            + "For Price range, use the prefix 'o_pr' followed by '/' and use '-' to indicate the range. \n"
            + "Example: " + COMMAND_WORD + " o_ar/flufy o_st/Pending o_pr/5.5-20.2";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    public static final String MESSAGE_INVALID_OS = "Order status has to be either "
            + "'Pending', 'Negotiating', or 'Delivering'";
    public static final String MESSAGE_INVALID_PARAMETER = "The correct format for price range should be"
            + "lower - upper \n"
            + "e.g: 20- 200";

    private final Predicate<Order> additionalRequestPredicate;
    private final Predicate<Order> orderStatusPredicate;
    private final Predicate<Order> priceRangePredicate;

    /**
     * Creates a FilterLocCommand to filter the specified {@code Location}.
     */
    public FilterOrderCommand(Predicate<Order> additionalRequestPredicate, Predicate<Order> orderStatusPredicate,
                              Predicate<Order> priceRangePredicate) {
        this.additionalRequestPredicate = additionalRequestPredicate;
        this.orderStatusPredicate = orderStatusPredicate;
        this.priceRangePredicate = priceRangePredicate;
    }

    /**
     * Creates a Predicate to filter the specified {@code Pet}.
     */
    public Predicate<Order> generatePredicate() {
        return order -> additionalRequestPredicate.test(order) && orderStatusPredicate.test(order)
                && priceRangePredicate.test(order);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Order> finalPredicate = generatePredicate();
        model.updateFilteredOrderList(finalPredicate);
        model.switchToOrderList();
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredCurrList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterOrderCommand // instanceof handles nulls
                && additionalRequestPredicate.equals(((FilterOrderCommand) other).additionalRequestPredicate)
                && orderStatusPredicate.equals(((FilterOrderCommand) other).orderStatusPredicate)
                && priceRangePredicate.equals(((FilterOrderCommand) other).priceRangePredicate)); // state check
    }
}
