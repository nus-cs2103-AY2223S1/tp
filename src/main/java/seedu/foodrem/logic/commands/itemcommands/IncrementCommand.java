package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemQuantity;

/**
 * Increments the quantity of an item by a specified amount.
 */
public class IncrementCommand extends Command {
    public static final String COMMAND_WORD = "inc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Increments the quantity of the ttem identified"
            + "by the index number used in the displayed item list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY] "
            + "Example: " + COMMAND_WORD + " 10 ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Incremented Item: %1$s";
    public static final String MESSAGE_NOT_INCREMENTED = "Quantity to increment by must be provided.";
    private final Index index;
    private final ItemQuantity quantity;

    /**
     * @param index    of the item in the filtered item list to increment.
     * @param quantity quantity of the item to increment.
     */
    public IncrementCommand(Index index, ItemQuantity quantity) {
        requireNonNull(index);
        requireNonNull(quantity);

        this.index = index;
        this.quantity = quantity;
    }

    /**
     * Creates and returns a {@code Item} with the quantity of {@code itemToEdit}
     * incremented by  {@code editItemDescriptor}.
     */
    private static Item createIncrementedItem(Item itemToIncrement, ItemQuantity quantity) {
        assert itemToIncrement != null;

        ItemQuantity incrementedQuantity = itemToIncrement.getQuantity().incrementQuantity(quantity);

        return new Item(itemToIncrement.getName(),
                incrementedQuantity,
                itemToIncrement.getUnit(),
                itemToIncrement.getBoughtDate(),
                itemToIncrement.getExpiryDate());
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }

        Item itemToIncrement = lastShownList.get(index.getZeroBased());
        Item incrementedItem = createIncrementedItem(itemToIncrement, quantity);

        model.setItem(itemToIncrement, incrementedItem);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, incrementedItem));
    }
}
