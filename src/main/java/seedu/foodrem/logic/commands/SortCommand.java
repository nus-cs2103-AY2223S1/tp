package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.util.ChainComparator;

/**
 * Sorts a list of all items in FoodRem depending on the
 * provided comparator. The comparator is specified by the
 * Sort Command, and supports the following sorting criteria:
 * - Name
 * - Quantity
 * - Type
 * - Bought Date
 * - Expiry Date
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Sorts all items according to a specified criteria."
                    + "Available criteria includes sorting by name, quantity, type, bought date, expiry date.\n"
                    + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                    + "Example: " + COMMAND_WORD + "name";

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
