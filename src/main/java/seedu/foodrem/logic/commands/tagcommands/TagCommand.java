package seedu.foodrem.logic.commands.tagcommands;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_TAG_NAME;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the item identified by the index number used in the displayed item list with a valid Tag.\n"
            + "Parameters: " + PREFIX_ID + " INDEX (must be a positive integer) "+ PREFIX_TAG_NAME + "TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG_NAME + "Condiments " + PREFIX_ID + "1";

    public static final String MESSAGE_SUCCESS = "Item tagged successfully";

    public static final String MESSAGE_DUPLICATE_TAG = "This item has already been tagged with this tag";

    public static final String MESSAGE_TAG_DOES_NOT_EXIST ="This tag does not exist";

    public static final String MESSAGE_ITEM_INDEX_DOES_NOT_EXIST ="The item index does not exist";

    private final Index index;
    private final Tag tag;

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

        itemToTag.addTag(tag);

        return new CommandResult(MESSAGE_SUCCESS);

    }
}
