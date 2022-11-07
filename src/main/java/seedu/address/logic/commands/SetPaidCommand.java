package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;

/**
 * Sets the payment status of a bill as paid.
 */
public class SetPaidCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("setpaid", "sp");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the payment status of the bill identified "
            + "by the index number used in the displayed bill list as PAID.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_PAID_BILL_SUCCESS = "Set bill as paid: %1$s";

    public static final String MESSAGE_ALREADY_PAID = "This bill is already paid";

    public static final String MESSAGE_INVALID_INDEX = "The index provided is invalid";

    private final Index indexOfBill;

    /**
     * Creates a SetPaidCommand to set the payment status of the specified {@code Bill} as PAID
     *
     * @param indexOfBill Index of the bill in the filtered bill list to set as paid
     */
    public SetPaidCommand(Index indexOfBill) {
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

        Bill billToSetAsPaid = lastShownBillList.get(indexOfBill.getZeroBased());

        if (billToSetAsPaid.getPaymentStatus().isPaid()) {
            throw new CommandException(MESSAGE_ALREADY_PAID);
        }

        model.setBillAsPaid(billToSetAsPaid);
        return new CommandResult(String.format(MESSAGE_SET_PAID_BILL_SUCCESS, billToSetAsPaid));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetPaidCommand // instanceof handles nulls
                && indexOfBill.equals(((SetPaidCommand) other).indexOfBill)); // state check
    }

    public Index getIndexOfBill() {
        return indexOfBill;
    }

}
