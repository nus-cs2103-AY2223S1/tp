package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandType.SORT_COMMAND;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.util.ChainComparator;

/**
 * Sorts a list of all items in FoodRem depending on the
 * provided comparator. The comparator is specified by the
 * Sort Command, and supports the following sorting criteria:
 * - Name
 * - Quantity
 * - Unit
 * - Bought Date
 * - Expiry Date
 */
public class SortCommand extends Command {
    private final ChainComparator<Item> comparator;

    public SortCommand(ChainComparator<Item> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedItemList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_SORTED_OVERVIEW, model.getSortedItemList().size()));
    }

    public static String getUsage() {
        return SORT_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
