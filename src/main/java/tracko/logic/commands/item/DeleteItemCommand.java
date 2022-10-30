package tracko.logic.commands.item;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;
import tracko.model.item.InventoryItem;
import tracko.model.item.exceptions.ItemUnmodifiableException;

/**
 * Deletes an item identified using it's displayed index in TrackO.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "deletei";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item:\n%1$s";

    public static final String MESSAGE_UNCOMPLETED_ORDER_ITEM = "Item cannot be deleted, there exists uncompleted "
        + "orders for item:\n%1$s";

    private final Index targetIndex;

    public DeleteItemCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<InventoryItem> lastShownList = model.getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        InventoryItem inventoryItemToDelete = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.deleteItem(inventoryItemToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, inventoryItemToDelete));
        } catch (ItemUnmodifiableException e) {
            return new CommandResult(String.format(MESSAGE_UNCOMPLETED_ORDER_ITEM, inventoryItemToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteItemCommand) other).targetIndex)); // state check
    }
}
