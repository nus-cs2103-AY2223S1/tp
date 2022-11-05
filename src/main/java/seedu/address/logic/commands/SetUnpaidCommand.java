package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;

/**
 * Sets the payment status of a bill to UNPAID.
 */
public class SetUnpaidCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("setunpaid",
            "sup");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the payment status of the bill identified "
            + "by the index number used in the displayed bill list as UNPAID.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_UNPAID_BILL_SUCCESS = "Set bill as unpaid: %1$s";

    public static final String MESSAGE_ALREADY_SET_UNPAID = "This bill is already set as unpaid";

    public static final String MESSAGE_INVALID_INDEX = "The index provided is invalid";

    private final Index indexOfBill;

    /**
     * Creates a SetUnpaidCommand to set the payment status of the specified {@code Bill} as UNPAID
     *
     * @param indexOfBill Index of the bill in the filtered bill list to set as paid
     */
    public SetUnpaidCommand(Index indexOfBill) {
        requireNonNull(indexOfBill);
        this.indexOfBill = indexOfBill;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bill> lastShownBillList = model.getFilteredBillList();

        if (indexOfBill.getZeroBased() >= lastShownBillList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Bill billToSetAsUnpaid = lastShownBillList.get(indexOfBill.getZeroBased());

        if (!billToSetAsUnpaid.getPaymentStatus().isPaid()) {
            throw new CommandException(MESSAGE_ALREADY_SET_UNPAID);
        }

        model.setBillAsUnpaid(billToSetAsUnpaid);
        return new CommandResult(String.format(MESSAGE_SET_UNPAID_BILL_SUCCESS, billToSetAsUnpaid));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetUnpaidCommand // instanceof handles nulls
                && indexOfBill.equals(((SetUnpaidCommand) other).indexOfBill)); // state check
    }

    public Index getIndexOfBill() {
        return indexOfBill;
    }
}
