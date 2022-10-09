package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListOrdersCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getOrderList();
        return new CommandResult(MESSAGE_SUCCESS);
        // TODO: implement list functionality for orders
    }
}
