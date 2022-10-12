package seedu.foodrem.logic.commands.tagcommands;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_TAG_NAME;

public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untags the item identified by the index number used in the displayed item list with a valid Tag.\n"
            + "Parameters: " + PREFIX_TAG_NAME + "TAG_NAME " + PREFIX_ID + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG_NAME + "Condiments " + PREFIX_ID + "1";

    public static final String MESSAGE_SUCCESS = "Item untagged successfully";

    public static final String ITEM_NOT_TAGGED = "This item is not tagged with this tag";

    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "This tag does not exist";

    public static final String MESSAGE_ITEM_INDEX_DOES_NOT_EXIST = "The item index does not exist";

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

    /**
     * Creates and returns a {@code Item} with the tagSet of {@code itemToUntag}
     * edited
     */
    private static Item createUntaggedItem(Item itemToUntag, Tag tag) {
        assert itemToUntag != null;

        itemToUntag.removeTag(tag);
        Set<Tag> newTagSet = itemToUntag.getTagSet();

        return new Item(itemToUntag.getName(),
                itemToUntag.getQuantity(),
                itemToUntag.getUnit(),
                itemToUntag.getBoughtDate(),
                itemToUntag.getExpiryDate(),
                newTagSet
        );
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }

        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_ITEM_INDEX_DOES_NOT_EXIST);
        }

        Item itemToUntag = lastShownList.get(index.getZeroBased());

        if (!itemToUntag.containsTag(tag)) {
            throw new CommandException(ITEM_NOT_TAGGED);
        }

        Item newTagSetItem = createUntaggedItem(itemToUntag, tag);

        model.setItem(itemToUntag, newTagSetItem);

        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(MESSAGE_SUCCESS);

    }


}
