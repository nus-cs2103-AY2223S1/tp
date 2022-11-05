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
import seedu.address.model.person.Deliverer;


/**
 * Deletes a Deliverer identified using it's displayed index from the address book.
 */
public class DeleteDelivererCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Deliverer identified by index used in the displayed Deliverer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DELIVERER_SUCCESS = "Deleted Deliverer: %1$s";

    public static final String MESSAGE_DELETE_DELIVERER_FAILURE = "Unable to execute DeleteDelivererCommand.";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}.
     */
    public DeleteDelivererCommand(Index targetIndex) {
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
        if (!(o instanceof Deliverer)) {
            throw new CommandException(String.format(Messages.INVALID_DELIVERER, targetIndex.getOneBased()));
        }

        Deliverer personToDelete = (Deliverer) o;

        model.deleteDeliverer(personToDelete);

        List<Order> ordersFromDeliverer = model.getOrdersFromDeliverer(personToDelete);

        for (Order order: ordersFromDeliverer) {
            model.deleteOrder(order);
        }
        model.switchToDelivererList();
        return new CommandResult(String.format(MESSAGE_DELETE_DELIVERER_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDelivererCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDelivererCommand) other).targetIndex)); // state check
    }
}

