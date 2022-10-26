package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.REMARK_COMMAND;

import java.util.List;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Adds a remark to an item in FoodRem.
 */
public class RemarkCommand extends Command {
    private final Index index;
    private final ItemRemark remark;

    /**
     * @param index  of the item in the filtered item list to increment.
     * @param remark the remark to be added to the item.
     */
    public RemarkCommand(Index index, ItemRemark remark) {
        requireNonNull(index);
        requireNonNull(remark);

        this.index = index;
        this.remark = remark;
    }

    /**
     * Creates and returns an {@code Item} with the remark of {@code itemToRemark}
     */
    private static Item createItemWithRemark(Item itemToRemark, ItemRemark remark) {
        assert itemToRemark != null;

        return new Item(itemToRemark.getName(),
                itemToRemark.getQuantity(),
                itemToRemark.getUnit(),
                itemToRemark.getBoughtDate(),
                itemToRemark.getExpiryDate(),
                itemToRemark.getPrice(),
                remark,
                itemToRemark.getTagSet());
    }

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getCurrentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }

        Item itemToRemark = lastShownList.get(index.getZeroBased());
        Item remarkedItem = createItemWithRemark(itemToRemark, remark);

        model.setItem(itemToRemark, remarkedItem);
        return CommandResult.from(new ItemWithMessage(remarkedItem,
                "Remark has been updated. View the updated item below:"));
    }

    public static String getUsage() {
        return REMARK_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RemarkCommand
                && index.equals(((RemarkCommand) other).index)
                && remark.equals(((RemarkCommand) other).remark));
    }
}
