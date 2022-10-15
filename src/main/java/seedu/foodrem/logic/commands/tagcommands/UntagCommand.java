package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandWord.UNTAG_COMMAND;

import java.util.List;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * Untags an item with a Tag.
 */
public class UntagCommand extends Command {
    // TODO: Test this command
    private static final String MESSAGE_SUCCESS = "Item untagged successfully";
    private static final String ERROR_DUPLICATE = "This item is not tagged with this tag";
    private static final String ERROR_NOT_FOUND_TAG = "This tag does not exist";
    private static final String ERROR_NOT_FOUND_ITEM = "The item index does not exist";

    private final Index index;
    private final Tag tag;

    /**
     * Creates a TagCommand to tag the specified {@code Item} with a specified {@code Tag}
     */
    public UntagCommand(String tagName, Index index) {
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

        List<Item> lastShownList = model.getFilteredItemList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(ERROR_NOT_FOUND_ITEM);
        }

        Item itemToUntag = lastShownList.get(index.getZeroBased());
        if (!itemToUntag.containsTag(tag)) {
            throw new CommandException(ERROR_DUPLICATE);
        }

        Item newTagSetItem = Item.createUntaggedItem(itemToUntag, tag);

        model.setItem(itemToUntag, newTagSetItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getUsage() {
        return UNTAG_COMMAND.getUsage();
    }
}
