package tracko.logic.commands.item;

import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;

/**
 * Lists all items in the inventory to the user.
 */
public class ListItemsCommand extends Command {
    public static final String COMMAND_WORD = "listi";

    public static final String MESSAGE_SUCCESS = "Listed all items.";

    @Override
    public CommandResult execute(Model model) {
        // requireNonNull(model);
        // model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // return new CommandResult(MESSAGE_SUCCESS);
        // TODO: implement list functionality for orders
        return null;
    }
}
