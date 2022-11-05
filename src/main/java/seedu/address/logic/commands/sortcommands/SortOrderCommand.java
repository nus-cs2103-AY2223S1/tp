package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Sorts the order list.
 */
public class SortOrderCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS = "order list has been sorted successfully";
    public static final String MESSAGE_USAGE =
            "Acceptable order attributes are pricerange, duedate, price, orderstatus";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting order list \n%2$s";

    private final Comparator<Order> comparator;

    /**
     * Constructs a sortOrderCommand with specified comparator.
     * @param comparator The specified comparator.
     */
    public SortOrderCommand(Comparator<Order> comparator) {
        requireNonNull(comparator);

        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortOrder(comparator);
        model.switchToOrderList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
