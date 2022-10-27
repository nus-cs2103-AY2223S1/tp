package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.TAG_COMMAND;

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
    private static final String MESSAGE_SUCCESS = "Item tagged successfully. Updated item:";

    private final Index index;
    private final Tag tag;

    /**
     * Creates a TagCommand to tag the specified {@code Item} with a specified {@code Tag}
     *
     * @param tagName the name of the tag
     * @param index the index of the item to tag
     */
    public TagCommand(String tagName, Index index) {
        requireNonNull(tagName);
        requireNonNull(index);
        this.index = index;
        this.tag = new Tag(tagName);
    }

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        Item itemToTag = TagCommandUtil.getItemWithValidation(model, tag, index);

        Set<Tag> itemTags = itemToTag.getTagSet();
        if (itemTags.contains(tag)) {
            throw new CommandException("This item has already been tagged with this tag");
        }

        itemTags.add(tag);
        Item newTagSetItem = Item.createItemWithTags(itemToTag, itemTags);
        model.setItem(itemToTag, newTagSetItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return CommandResult.from(new ItemWithMessage(newTagSetItem, MESSAGE_SUCCESS));
    }

    public static String getUsage() {
        return TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagCommand
                && index.equals(((TagCommand) other).index)
                && tag.equals(((TagCommand) other).tag));
    }
}
