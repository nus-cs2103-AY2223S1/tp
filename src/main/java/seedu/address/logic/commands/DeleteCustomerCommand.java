package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Deletes a customer identified using it's displayed index from the address book.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "delcus";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer: %1$s";

    private final Index targetIndex;

    public DeleteCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage...storage) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getSortedFilteredCustomerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        model.selectTab(GuiTab.CUSTOMER);
        Customer selectedCustomer = model.getSelectedCustomer().getValue();
        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCustomer(customerToDelete);
        if (!model.isSameCustomerAsSelectedCustomer(customerToDelete)) {
            model.selectCustomer(selectedCustomer);
        }

        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCustomerCommand) other).targetIndex)); // state check
    }
}
