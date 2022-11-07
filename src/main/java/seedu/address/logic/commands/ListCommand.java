package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Lists all customers in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all customers";


    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        model.selectTab(GuiTab.CUSTOMER);
        if (!model.hasSelectedCustomer()) {
            List<Customer> lastShownList = model.getSortedFilteredCustomerList();
            if (lastShownList.size() > 0) {
                Customer customerToOpen = lastShownList.get(0);
                model.selectCustomer(customerToOpen);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
