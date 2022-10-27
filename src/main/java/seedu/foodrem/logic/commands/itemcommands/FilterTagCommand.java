package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.FILTER_TAG_COMMAND;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.TagSetContainsTagPredicate;
import seedu.foodrem.model.tag.Tag;

/**
 * Filters all items in FoodRem for items that contain the specified tag
 */
public class FilterTagCommand extends Command {
    private final TagSetContainsTagPredicate pred;

    /**
     * @param tag to filter the Item list for Items tagged with it
     */
    public FilterTagCommand(Tag tag) {
        requireNonNull(tag);
        this.pred = new TagSetContainsTagPredicate(tag);
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(pred.getTag())) {
            throw new CommandException("This tag does not exist in the FoodRem");
        }

        model.updateFilteredItemList(pred);
        return CommandResult.from(String.format(this.getSuccessMessage(),
                model.getCurrentList().size()));
    }

    public static String getUsage() {
        return FILTER_TAG_COMMAND.getUsage();
    }

    protected String getSuccessMessage() {
        return String.format("Filtered by tag: %s\n%s",
                this.pred.getTag().getName(),
                Messages.MESSAGE_ITEMS_FILTERED_OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FilterTagCommand
                && this.pred.equals(((FilterTagCommand) other).pred));
    }
}
