package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_OPEN_CUSTOMER_SUCCESS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

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

    private final Index targetIndex;

    public OpenCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public OpenCustomerCommand() {
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        model.selectTab(GuiTab.CUSTOMER);
        List<Customer> lastShownList = model.getSortedFilteredCustomerList();
        if (targetIndex == null) {
            return new CommandResult(MESSAGE_USAGE);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToOpen = lastShownList.get(targetIndex.getZeroBased());
        model.selectCustomer(customerToOpen);
        return new CommandResult(String.format(MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToOpen));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OpenCustomerCommand)) {
            return false;
        }
        return Objects.equals(targetIndex, ((OpenCustomerCommand) other).targetIndex);
    }
}
