package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Lists all items in FoodRem to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = CommandWord.LIST_COMMAND.getCommandWord();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all items in FoodRem."
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Listed all items";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
