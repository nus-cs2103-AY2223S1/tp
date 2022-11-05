package seedu.address.logic.commands.iteration;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.iteration.Iteration;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Adds an existing iteration from a commission in ArtBuddy.
 */
public class DeleteIterationCommand extends Command {

    public static final String COMMAND_WORD = "deliter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the iteration identified by the index number used in the displayed iteration list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITERATION_SUCCESS = "Deleted Iteration: %1$s";

    private final Index targetIndex;

    /**
     * Constructs a {@code DeleteIterationCommand} that deletes the Iteration in the currently selected
     * Commission with the given {@code targetIndex}.
     */
    public DeleteIterationCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCommission()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_COMMISSION);
        }

        Commission activeCommission = model.getSelectedCommission().getValue();
        List<Iteration> lastShownList = activeCommission.getIterationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX);
        }

        Iteration iterationToDelete = lastShownList.get(targetIndex.getZeroBased());
        activeCommission.removeIteration(iterationToDelete);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(MESSAGE_DELETE_ITERATION_SUCCESS, iterationToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIterationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteIterationCommand) other).targetIndex)); // state check
    }
}
