package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Set;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.enums.CommandWord;
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

    public static final String MESSAGE_SUCCESS = "Item tagged successfully";

    public static final String MESSAGE_DUPLICATE_TAG = "This item has already been tagged with this tag";

    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "This tag does not exist";

    public static final String MESSAGE_ITEM_INDEX_DOES_NOT_EXIST = "The item index does not exist";

    private static final String COMMAND_WORD = CommandWord.TAG_COMMAND.getCommandWord();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the item identified by the index number used in the displayed item list with a valid Tag.\n"
            + "Parameters: " + PREFIX_NAME + "TAG_NAME" + PREFIX_ID + "INDEX (item index must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Condiments " + PREFIX_ID + "1";

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
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }

        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_ITEM_INDEX_DOES_NOT_EXIST);
        }

        Item itemToTag = lastShownList.get(index.getZeroBased());

        if (itemToTag.containsTag(tag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        Item newTagSetItem = createTaggedItem(itemToTag, tag);

        model.setItem(itemToTag, newTagSetItem);

        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(MESSAGE_SUCCESS);

    }
}
