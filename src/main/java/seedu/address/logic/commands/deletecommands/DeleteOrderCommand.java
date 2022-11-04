package seedu.address.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Deletes a Order identified using it's displayed index from the address book.
 */
public class DeleteOrderCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Order identified by index used in the displayed Order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    public static final String MESSAGE_DELETE_ORDER_FAILURE = "Unable to execute DeleteOrderCommand.";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}.
     */
    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Object> lastShownList = model.getFilteredCurrList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Object o = lastShownList.get(targetIndex.getZeroBased());
        if (!(o instanceof Order)) {
            throw new CommandException(String.format(Messages.INVALID_ORDER, targetIndex.getOneBased()));
        }

        Order orderToDelete = (Order) o;

        model.deleteOrder(orderToDelete);

        model.switchToOrderList();
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}
