package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.sortcomparators.Order.OrderType.DESC;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;

/**
 * Sorts the buyers list.
 */
public class SortBuyersCommand extends Command {

    public static final String COMMAND_WORD = "sortbuyers";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts buyers by name, budget, priority, or entry time in ascending or descending order."
            + " You can only sort by one criteria at a time.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + " NAME <ASC/DESC>] "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE <ASC/DESC>]"
            + "[" + PREFIX_PRIORITY + " PRIORITY <ASC/DESC>]"
            + "[" + PREFIX_TIME + " TIME <ASC/DESC>]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " " + DESC;

    public static final String MESSAGE_SUCCESS = "Sorted buyers by: %s";

    private final Comparator<Buyer> comparator;

    /**
     * Creates a SortBuyerCommand to add the specified {@code Buyer}
     * @param comparator
     */
    public SortBuyersCommand(Comparator<Buyer> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortBuyerList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortBuyersCommand // instanceof handles nulls
                && comparator.equals(((SortBuyersCommand) other).comparator));
    }
}
