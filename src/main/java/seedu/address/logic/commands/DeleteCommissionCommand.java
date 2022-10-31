package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMMISSIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Deletes the commission of an existing customer in the ArtBuddy.
 */
public class DeleteCommissionCommand extends Command {

    public static final String COMMAND_WORD = "delcom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the commission identified by the index number used in the displayed commission list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_COMMISSION_SUCCESS = "Deleted Commission: %1$s";

    private final Index targetIndex;

    public DeleteCommissionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage...storage) throws CommandException {
        requireNonNull(model);
        List<Commission> lastShownList = model.getFilteredCommissionList();
        if (lastShownList == null || targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
        }

        Commission selectedCommission = model.getSelectedCommission().getValue();
        Commission commissionToDelete = lastShownList.get(targetIndex.getZeroBased());
        Customer customer = commissionToDelete.getCustomer();
        model.removeCommission(customer, commissionToDelete);
        model.selectTab(GuiTab.COMMISSION);
        model.updateFilteredCommissionList(PREDICATE_SHOW_ALL_COMMISSIONS);
        if (selectedCommission != null && !selectedCommission.isSameCommission(commissionToDelete)) {
            model.selectCommission(selectedCommission);
        } else {
            model.selectCommission(null);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_COMMISSION_SUCCESS, commissionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommissionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommissionCommand) other).targetIndex)); // state check
    }
}
