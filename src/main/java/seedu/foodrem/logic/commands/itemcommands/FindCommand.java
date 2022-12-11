package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.FIND_COMMAND;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;

/**
 * Finds and lists all items in FoodRem whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    private final NameContainsKeywordsPredicate predicate;

    /**
     * Constructs the FindCommand.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        return CommandResult.from(
                String.format(model.getCurrentList().size() == 1
                                ? Messages.MESSAGE_ITEM_FILTERED_OVERVIEW
                                : Messages.MESSAGE_ITEMS_FILTERED_OVERVIEW,
                        model.getCurrentList().size()));
    }

    /**
     * Returns a string representing how to use the command.
     *
     * @return a string representing how to use the command.
     */
    public static String getUsage() {
        return FIND_COMMAND.getUsage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindCommand
                && predicate.equals(((FindCommand) other).predicate));
    }
}
