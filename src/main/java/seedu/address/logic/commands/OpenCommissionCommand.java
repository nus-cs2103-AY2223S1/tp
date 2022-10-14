package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.ui.GuiTab;

/**
 * Selects a commission and shows their details.
 */
public class OpenCommissionCommand extends Command {
    public static final String COMMAND_WORD = "opencom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a commission and shows commission's details and iterations\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_OPEN_COMMISSION_SUCCESS = "Opened Commission: %1$s";

    private final Index targetIndex;

    public OpenCommissionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCustomer()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_CUSTOMER);
        }

        List<Commission> lastShownList = model.getFilteredCommissionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
        }

        Commission commissionToOpen = lastShownList.get(targetIndex.getZeroBased());
        model.selectCommission(commissionToOpen);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(MESSAGE_OPEN_COMMISSION_SUCCESS, commissionToOpen));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCommissionCommand // instanceof handles nulls
                && targetIndex.equals(((OpenCommissionCommand) other).targetIndex)); // state check
    }
}
