package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.LIST_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Lists all items in FoodRem to the user.
 */
public class ListCommand extends Command {
    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);
        return CommandResult.from("Listed all items");
    }

    public static String getUsage() {
        return LIST_COMMAND.getUsage();
    }
}
