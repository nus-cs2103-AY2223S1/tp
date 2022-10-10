package tracko.logic.commands.item;

import javafx.collections.transformation.FilteredList;
import tracko.commons.core.Messages;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.order.FindOrderCommand;
import tracko.model.Model;
import tracko.model.items.Item;
import tracko.model.items.ItemContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class FindItemCommand extends Command {
    public static final String COMMAND_WORD = "findi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " blue shirt";

    private final ItemContainsKeywordsPredicate predicate;

    public FindItemCommand(ItemContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);

        if (model.doesFilteredItemListContainOneItem()) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ONE_ITEM_FOUND, model.getFilteredItemList().size()));
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_FOUND_OVERVIEW, model.getFilteredItemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindItemCommand // instanceof handles nulls
                && predicate.equals(((FindItemCommand) other).predicate)); // state check
    }
}
