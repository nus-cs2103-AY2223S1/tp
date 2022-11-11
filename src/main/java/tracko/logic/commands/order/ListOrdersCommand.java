package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static tracko.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;

/**
 * Lists all {@code Order}s in TrackO to the user.
 */
public class ListOrdersCommand extends Command {

    public static final String COMMAND_WORD = "listo";

    public static final String MESSAGE_SUCCESS = "Listed all orders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
