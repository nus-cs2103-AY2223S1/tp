package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * Handles validation for tag commands
 */
public class TagCommandUtil {

    private TagCommandUtil() {}

    /**
     * Checks if a model has a tag and if the index is valid before getting the item.
     *
     * @param model the current model
     * @param tag the tag to be validated
     * @param index the index of the item
     */
    public static Item getItemWithValidation(Model model, Tag tag, Index index) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException("This tag does not exist");
        }

        List<Item> lastShownList = model.getCurrentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException("The item index does not exist");
        }

        return lastShownList.get(index.getZeroBased());
    }
}
