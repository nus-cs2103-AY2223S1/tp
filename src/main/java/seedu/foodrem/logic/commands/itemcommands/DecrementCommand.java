package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.DECREMENT_COMMAND;

import java.util.List;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Increments the quantity of an item by a specified amount.
 */
public class DecrementCommand extends Command {
    private final Index index;
    private final ItemQuantity quantity;

    /**
     * @param index    of the item in the filtered item list to increment.
     * @param quantity quantity of the item to increment.
     */
    public DecrementCommand(Index index, ItemQuantity quantity) {
        requireNonNull(index);
        requireNonNull(quantity);

        this.index = index;
        this.quantity = quantity;
    }

    /**
     * Creates and returns an {@code Item} with the quantity of {@code itemToEdit}
     * decremented by {@code editItemDescriptor}.
     */
    private static Item createDecrementedItem(Item itemToDecrement, ItemQuantity quantity) throws CommandException {
        assert itemToDecrement != null;

        ItemQuantity decrementedQuantity;
        try {
            decrementedQuantity = ItemQuantity.performArithmeticOperation(
                    itemToDecrement.getQuantity(), quantity, (x, y) -> x - y);
        } catch (IllegalArgumentException e) {
            throw new CommandException("(Final Quantity) " + e.getMessage());
        }

        return new Item(itemToDecrement.getName(),
                decrementedQuantity,
                itemToDecrement.getUnit(),
                itemToDecrement.getBoughtDate(),
                itemToDecrement.getExpiryDate(),
                itemToDecrement.getPrice(),
                itemToDecrement.getRemarks(),
                itemToDecrement.getTagSet());
    }

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getCurrentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }

        Item itemToDecrement = lastShownList.get(index.getZeroBased());
        Item decrementedItem = createDecrementedItem(itemToDecrement, quantity);

        model.setItem(itemToDecrement, decrementedItem);
        return CommandResult.from(new ItemWithMessage(decrementedItem,
                "Decremented successfully and updated item as follows:"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DecrementCommand // instanceof handles nulls
                && index.equals(((DecrementCommand) other).index)
                && quantity.equals(((DecrementCommand) other).quantity)); // state check
    }

    public static String getUsage() {
        return DECREMENT_COMMAND.getUsage();
    }
}
