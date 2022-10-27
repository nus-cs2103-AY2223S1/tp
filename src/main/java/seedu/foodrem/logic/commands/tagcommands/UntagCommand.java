package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.UNTAG_COMMAND;

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
 * Untags an item with a Tag.
 */
public class UntagCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Item untagged successfully. Updated item:";

    private final Index index;
    private final Tag tag;

    /**
     * Creates an UnTagCommand to untag the specified {@code Item} with a specified {@code Tag}
     *
     * @param tagName the name of the tag
     * @param index the index of the item to untag
     */
    public UntagCommand(String tagName, Index index) {
        requireNonNull(tagName);
        requireNonNull(index);
        this.index = index;
        this.tag = new Tag(tagName);
    }

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        Item itemToUntag = TagCommandUtil.getItemWithValidation(model, tag, index);

        Set<Tag> itemTags = itemToUntag.getTagSet();
        if (!itemTags.contains(tag)) {
            throw new CommandException("This item has not been tagged with this tag");
        }

        itemTags.remove(tag);
        Item newTagSetItem = Item.createItemWithTags(itemToUntag, itemTags);
        model.setItem(itemToUntag, newTagSetItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return CommandResult.from(new ItemWithMessage(newTagSetItem, MESSAGE_SUCCESS));
    }

    public static String getUsage() {
        return UNTAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UntagCommand
                && index.equals(((UntagCommand) other).index)
                && tag.equals(((UntagCommand) other).tag));
    }
}
