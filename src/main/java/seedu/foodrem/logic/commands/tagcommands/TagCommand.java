package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.TAG_COMMAND;

import java.util.List;
import java.util.Set;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Tags an item with a Tag.
 */
public class TagCommand extends Command {
    private static final String ERROR_DUPLICATE = "This item has already been tagged with this tag";
    private static final String ERROR_NOT_FOUND_TAG = "This tag does not exist";
    private static final String ERROR_NOT_FOUND_ITEM = "The item index does not exist";

    private final Index index;
    private final Tag tag;

    /**
     * Creates a TagCommand to tag the specified {@code Item} with a specified {@code Tag}
     */
    public TagCommand(String tagName, Index index) {
        requireNonNull(tagName);
        requireNonNull(index);
        this.index = index;
        this.tag = new Tag(tagName);
    }

    /**
     * Creates and returns a {@code Item} with the tagSet of {@code itemToEdit}
     * edited
     */
    private static Item createTaggedItem(Item itemToTag, Tag tag) {
        assert itemToTag != null;

        itemToTag.addItemTag(tag);
        Set<Tag> newTagSet = itemToTag.getTagSet();

        return new Item(itemToTag.getName(),
                itemToTag.getQuantity(),
                itemToTag.getUnit(),
                itemToTag.getBoughtDate(),
                itemToTag.getExpiryDate(),
                itemToTag.getPrice(),
                itemToTag.getRemarks(),
                newTagSet);
    }

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        Item itemToTag = validateAndGetTargetItem(model, tag, index);
        if (itemToTag.containsTag(tag)) {
            throw new CommandException(ERROR_DUPLICATE);
        }

        Item newTagSetItem = createTaggedItem(itemToTag, tag);
        model.setItem(itemToTag, newTagSetItem);

        return CommandResult.from(
                new ItemWithMessage(newTagSetItem, "Item tagged successfully. View updated item below:"));
    }

    static Item validateAndGetTargetItem(Model model, Tag tag, Index index) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(ERROR_NOT_FOUND_TAG);
        }

        List<Item> lastShownList = model.getCurrentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(ERROR_NOT_FOUND_ITEM);
        }

        return lastShownList.get(index.getZeroBased());
    }

    public static String getUsage() {
        return TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof TagCommand
                && index.equals(((TagCommand) other).index)
                && tag.equals(((TagCommand) other).tag));
    }
}
