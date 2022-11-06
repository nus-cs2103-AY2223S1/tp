package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;
import tracko.model.order.Order;

/**
 * Deletes an order identified using it's displayed index in TrackO.
 */
public class DeleteOrderCommand extends Command {

    public static final String COMMAND_WORD = "deleteo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order:\n%1$s";

    private final Index targetIndex;

    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Order> lastShownList = model.getSortedOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOrder(orderToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}
