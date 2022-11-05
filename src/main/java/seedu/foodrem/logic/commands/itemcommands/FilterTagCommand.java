package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.FILTER_TAG_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.TagSetContainsTagPredicate;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.FilterByTag;

/**
 * Filters all items in FoodRem for items that contain the specified tag
 */
public class FilterTagCommand extends Command {
    private final TagSetContainsTagPredicate pred;

    private final Tag tag;

    /**
     * @param tag to filter the Item list for Items tagged with it
     */
    public FilterTagCommand(Tag tag) {
        requireNonNull(tag);
        this.pred = new TagSetContainsTagPredicate(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult<FilterByTag> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException("This tag does not exist in FoodRem");
        }

        model.updateFilteredItemList(pred);
        String primaryMessage = "Filtered by tag:";
        String secondaryMessage = String.format("%s items filtered", model.getCurrentList().size());
        return CommandResult.from(new FilterByTag(tag, primaryMessage, secondaryMessage));
    }

    public static String getUsage() {
        return FILTER_TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FilterTagCommand
                && this.pred.equals(((FilterTagCommand) other).pred));
    }
}
