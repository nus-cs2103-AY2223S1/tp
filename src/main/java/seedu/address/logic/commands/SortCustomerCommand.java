package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_COMMISSION_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_NAME;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.SortDirection;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Sorts the customers in address book by the specified comparator.
 */
public class SortCustomerCommand extends Command {

    public static final String COMMAND_WORD = "sortcus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed customer list by one of the "
            + "following options: \n"
            + "Prefix:"
            + "\t" + PREFIX_SORT_CUSTOMER_NAME + ": the customer's name, "
            + "\t" + PREFIX_SORT_CUSTOMER_COMMISSION_COUNT + ": the number of commissions\n"
            + "Suffix: + (increasing) or - (decreasing)\n"
            + "Parameters: [n/(+/-)] [c/(+/-)] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT_CUSTOMER_NAME + "+"
            + " sorts the list in increasing order by name";

    private final Comparator<Customer> comparator;
    private final String comparatorDescription;
    private final SortDirection direction;

    /**
     * @param comparator comparator to use to sort the customer list
     * @param comparatorDescription description of the comparator
     * @param direction direction of the comparator
     */
    public SortCustomerCommand(Comparator<Customer> comparator, String comparatorDescription, SortDirection direction) {
        this.comparator = comparator;
        this.comparatorDescription = comparatorDescription;
        this.direction = direction;
    }

    @Override
    public CommandResult execute(Model model, Storage...storage) {
        requireNonNull(model);
        model.updateSortedCustomerList(comparator);
        model.selectTab(GuiTab.CUSTOMER);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_SORTED,
                        model.getSortedFilteredCustomerList().size(),
                        direction.toString(),
                        comparatorDescription));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCustomerCommand // instanceof handles nulls
                && comparator.equals(((SortCustomerCommand) other).comparator)); // state check
    }
}
