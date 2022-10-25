package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.SupplyItem;

/**
 * Deletes a SupplyItem identified using it's displayed index from the address book.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "deleteItem";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the SupplyItem identified by the index number used in the displayed supplyItem list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SUPPLY_ITEM_SUCCESS = "Deleted SupplyItem: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteItemCommand to delete SupplyItem at the specified {@code Index}.
     */
    public DeleteItemCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<SupplyItem> lastShownList = model.getFilteredSupplyItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
        }

        SupplyItem supplyItemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteSupplyItem(targetIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_SUPPLY_ITEM_SUCCESS, supplyItemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteItemCommand) other).targetIndex)); // state check
    }
}


