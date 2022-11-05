package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_OPEN_COMMISSION_SUCCESS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.storage.Storage;
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


    private final Index targetIndex;

    /** Creates an {@code OpenCommissionCommand} to open the specified commission at the given `targetIndex`. */
    public OpenCommissionCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /** Creates an {@code OpenCommissionCommand} to open the commissions tab. */
    public OpenCommissionCommand() {
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        model.selectTab(GuiTab.COMMISSION);

        if (targetIndex == null) {
            return new CommandResult(MESSAGE_USAGE);
        }

        List<Commission> lastShownList = model.getFilteredCommissionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
        }

        Commission commissionToOpen = lastShownList.get(targetIndex.getZeroBased());
        model.selectCommission(commissionToOpen);
        return new CommandResult(String.format(MESSAGE_OPEN_COMMISSION_SUCCESS, commissionToOpen));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OpenCommissionCommand)) {
            return false;
        }
        return Objects.equals(targetIndex, ((OpenCommissionCommand) other).targetIndex);
    }
}
