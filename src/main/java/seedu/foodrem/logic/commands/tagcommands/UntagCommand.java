package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.UNTAG_COMMAND;

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
    private static final String MESSAGE_SUCCESS = "Item untagged successfully.\n%1$s";
    private static final String ERROR_ITEM_DOES_NOT_CONTAIN_TAG = "This item is not tagged with this tag";
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
    public CommandResult<String> execute(Model model) throws CommandException {
        Item itemToUntag = TagCommand.validateAndGetTargetItem(model, tag, ERROR_NOT_FOUND_TAG, index, ERROR_NOT_FOUND_ITEM);
        if (!itemToUntag.containsTag(tag)) {
            throw new CommandException(ERROR_ITEM_DOES_NOT_CONTAIN_TAG);
        }

        Item newTagSetItem = Item.createUntaggedItem(itemToUntag, tag);

        model.setItem(itemToUntag, newTagSetItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return CommandResult.from(String.format(MESSAGE_SUCCESS, newTagSetItem));
    }

    public static String getUsage() {
        return UNTAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof UntagCommand
                && index.equals(((UntagCommand) other).index)
                && tag.equals(((UntagCommand) other).tag));
    }
}
