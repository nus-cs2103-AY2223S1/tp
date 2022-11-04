package seedu.address.logic.commands.checkcommands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deliverer;

import static java.util.Objects.requireNonNull;

/**
 * Checks the orders of a deliverer.
 */
public class CheckDelivererCommand extends CheckCommand {
    public static final String MESSAGE_SUCCESS = "Checking deliverer %1$s";

    /**
     * Constructs a CheckDelivererCommand with index specified.
     */
    public CheckDelivererCommand(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Object> lastShownList = model.getFilteredCurrList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Object o = lastShownList.get(index.getZeroBased());
        if (!(o instanceof Deliverer)) {
            throw new CommandException(String.format(Messages.INVALID_DELIVERER, index.getOneBased()));
        }

        Deliverer deliverer = (Deliverer) o;
        model.checkDelivererOrder(deliverer);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof CheckDelivererCommand) {
            CheckDelivererCommand other = (CheckDelivererCommand) object;
            return other.index.equals(this.index);
        }

        return false;
    }
}
