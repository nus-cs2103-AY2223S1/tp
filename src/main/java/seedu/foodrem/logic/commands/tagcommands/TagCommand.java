package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandType.TAG_COMMAND;

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
    private static final String MESSAGE_SUCCESS = "Item tagged successfully";
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
                newTagSet);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(ERROR_NOT_FOUND_TAG);
        }

        List<Item> lastShownList = model.getFilteredItemList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(ERROR_NOT_FOUND_ITEM);
        }

        Item itemToTag = lastShownList.get(index.getZeroBased());
        if (itemToTag.containsTag(tag)) {
            throw new CommandException(ERROR_DUPLICATE);
        }

        Item newTagSetItem = createTaggedItem(itemToTag, tag);

        model.setItem(itemToTag, newTagSetItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getUsage() {
        return TAG_COMMAND.getUsage();
    }
}
