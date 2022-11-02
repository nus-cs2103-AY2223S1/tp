package seedu.address.logic.commands.filtercommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Filters the attributes of an existing Order in the address book.
 */
public class FilterOrderCommand extends FilterCommand {
    public static final String COMMAND_WORD = "filter-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all pets with attributes: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/[KEYWORDS] PREFIX/[KEYWORDS] ...\n"
            + "There are three possible attributes to filter: Additional requests, Order status, Price range \n"
            + "For Additional requests, use the prefix 'ar' \n"
            + "For Order status, use the prefix 'os' \n"
            + "For Price range, use the prefix 'pr' followed by '/' and use '-' to indicate the range. \n"
            + "Example: " + COMMAND_WORD + " ar/flufy os/Pending pr/5.5-20.2";

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
        return new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return additionalRequestPredicate.test(order) && orderStatusPredicate.test(order)
                        && priceRangePredicate.test(order);
            }
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Order> finalPredicate = generatePredicate();
        model.updateFilteredOrderList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                false,
                false,
                true,
                ListCommand.LIST_ORDER,
                false,
                null,
                false,
                null,
                null);
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
