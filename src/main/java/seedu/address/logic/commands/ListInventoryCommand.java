package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLY_ITEMS;

import seedu.address.model.Model;

/**
 * Lists all supply items in the inventory.
 */
public class ListInventoryCommand extends Command {
    public static final String COMMAND_WORD = "listInventory";

    public static final String MESSAGE_SUCCESS = "List all items in inventory";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplyItemList(PREDICATE_SHOW_ALL_SUPPLY_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
