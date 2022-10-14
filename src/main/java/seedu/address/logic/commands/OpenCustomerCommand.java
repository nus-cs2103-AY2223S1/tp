package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;

/**
 * Selects a customer and shows their details.
 */
public class OpenCustomerCommand extends Command {
    public static final String COMMAND_WORD = "opencus";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a customer and shows customer details and switches tab "
            + "to commissions to show commissions made by the customer.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_OPEN_CUSTOMER_SUCCESS = "Opened Customer: %1$s";

    private final Index targetIndex;

    public OpenCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage...storage) throws CommandException {
        requireNonNull(model);

        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToOpen = lastShownList.get(targetIndex.getZeroBased());
        model.selectCustomer(customerToOpen);

        return new CommandResult(String.format(MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToOpen));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((OpenCustomerCommand) other).targetIndex)); // state check
    }
}
