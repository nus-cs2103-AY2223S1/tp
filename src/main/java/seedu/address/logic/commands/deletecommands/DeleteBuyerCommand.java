package seedu.address.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;

/**
 * Deletes a buyer identified using it's displayed index from the address book.
 */
public class DeleteBuyerCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the buyer identified by index used in the displayed buyer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BUYER_SUCCESS = "Deleted Buyer: %1$s";

    public static final String MESSAGE_DELETE_BUYER_FAILURE = "Unable to execute DeleteBuyerCommand.";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}.
     */
    public DeleteBuyerCommand(Index targetIndex) {
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
        if (!(o instanceof Buyer)) {
            throw new CommandException(String.format(Messages.INVALID_BUYER, targetIndex.getOneBased()));
        }

        Buyer personToDelete = (Buyer) o;

        model.deleteBuyer(personToDelete);

        List<Order> ordersFromBuyer = model.getOrdersFromBuyer(personToDelete);

        for (Order order: ordersFromBuyer) {
            model.deleteOrder(order);
        }
        model.switchToBuyerList();
        return new CommandResult(String.format(MESSAGE_DELETE_BUYER_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBuyerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBuyerCommand) other).targetIndex)); // state check
    }
}
