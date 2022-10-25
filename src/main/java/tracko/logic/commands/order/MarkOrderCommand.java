package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static tracko.logic.parser.CliSyntax.FLAG_DELIVERED;
import static tracko.logic.parser.CliSyntax.FLAG_PAID;

import javafx.collections.ObservableList;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;
import tracko.model.order.Order;

/**
 * Marks the order identified with it's displayed index in the displayed order list as delivered and/or paid.
 */
public class MarkOrderCommand extends Command {

    public static final String COMMAND_WORD = "marko";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer) " + FLAG_PAID + " (optional) "
            + FLAG_DELIVERED + " (optional) \n"
            + "Example: " + COMMAND_WORD + " 1 " + FLAG_PAID + " " + FLAG_DELIVERED;

    public static final String MESSAGE_MARK_ORDER_SUCCESS = "Marked Order:\n%1$s";

    private final Index targetIndex;
    private final boolean isPaid;
    private final boolean isDelivered;

    /**
     * Creates a MarkOrderCommand object.
     * @param targetIndex Index of order in the displayed order list to be marked
     * @param isPaid
     * @param isDelivered
     */
    public MarkOrderCommand(Index targetIndex, boolean isPaid, boolean isDelivered) {
        this.targetIndex = targetIndex;
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Order> lastShownList = model.getSortedOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToMark = lastShownList.get(targetIndex.getZeroBased());

        if (isPaid && orderToMark.getPaidStatus()) {
            throw new CommandException(Messages.MESSAGE_ORDER_ALREADY_PAID);
        }

        if (isDelivered && orderToMark.getDeliveryStatus()) {
            throw new CommandException(Messages.MESSAGE_ORDER_ALREADY_DELIVERED);
        }

        // if order is marked paid and delivered in the same command when there is insufficient stock,
        // the order will not be marked as paid either.
        if (isDelivered && !orderToMark.isDeliverable()) {
            throw new CommandException(Messages.MESSAGE_INSUFFICIENT_STOCK);
        }

        model.markOrder(orderToMark, isPaid && !orderToMark.getPaidStatus(),
                isDelivered && !orderToMark.getDeliveryStatus());

        model.refreshData();

        return new CommandResult(String.format(MESSAGE_MARK_ORDER_SUCCESS, orderToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkOrderCommand // instanceof handles nulls
                && targetIndex.equals(((MarkOrderCommand) other).targetIndex)
                && isPaid == ((MarkOrderCommand) other).isPaid
                && isDelivered == ((MarkOrderCommand) other).isDelivered); // state check
    }
}
