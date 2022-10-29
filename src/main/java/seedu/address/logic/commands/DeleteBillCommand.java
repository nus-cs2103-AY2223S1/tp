package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;

/**
 * Deletes a bill identified using it's displayed index from the HealthContact.
 */
public class DeleteBillCommand extends Command {

    public static final CommandWord COMMAND_WORD =
            new CommandWord("deletebill", "db");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the bill identified by the index number used in the displayed bill list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BILL_SUCCESS = "Deleted Bill: %1$s";

    private final Index targetIndex;

    public DeleteBillCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bill> lastShownList = model.getFilteredBillList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BILL_DISPLAYED_INDEX);
        }

        Bill billToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBill(billToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BILL_SUCCESS, billToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBillCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBillCommand) other).targetIndex)); // state check
    }
}

