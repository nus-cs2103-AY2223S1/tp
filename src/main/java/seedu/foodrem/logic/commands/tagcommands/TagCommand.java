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

/**
 * Tags an item with a Tag.
 */
public class TagCommand extends Command {
    // TODO: Test this command
    private static final String MESSAGE_SUCCESS = "Item tagged successfully.\n%1$s";
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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(ERROR_NOT_FOUND_TAG);
        }

        List<Item> lastShownList = model.getCurrentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(ERROR_NOT_FOUND_ITEM);
        }

        Item itemToTag = lastShownList.get(index.getZeroBased());
        Set<Tag> itemTags = itemToTag.getTagSet();
        if (itemTags.contains(tag)) {
            throw new CommandException(ERROR_DUPLICATE);
        }
        itemTags.add(tag);
        Item newTagSetItem = Item.createItemWithTags(itemToTag, itemTags);

        model.setItem(itemToTag, newTagSetItem);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTagSetItem));
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
