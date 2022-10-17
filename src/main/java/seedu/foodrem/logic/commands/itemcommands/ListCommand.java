package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandType.LIST_COMMAND;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Lists all items in FoodRem to the user.
 */
public class ListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Listed all items";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getUsage() {
        return LIST_COMMAND.getUsage();
    }
}
