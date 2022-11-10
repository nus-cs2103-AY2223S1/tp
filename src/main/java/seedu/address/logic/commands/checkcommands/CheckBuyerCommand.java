package seedu.address.logic.commands.checkcommands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;

/**
 * Checks which Orders belong to the Buyer at the specified index.
 */
public class CheckBuyerCommand extends CheckCommand {
    public static final String MESSAGE_SUCCESS = "Checking buyer %1$s";

    /**
     * Constructs a CheckBuyerCommand with index specified.
     */
    public CheckBuyerCommand(Index index) {
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
        if (!(o instanceof Buyer)) {
            throw new CommandException(String.format(Messages.INVALID_BUYER, index.getOneBased()));
        }

        Buyer buyer = (Buyer) o;
        model.checkBuyerOrder(buyer);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof CheckBuyerCommand) {
            CheckBuyerCommand other = (CheckBuyerCommand) object;
            return other.index.equals(this.index);
        }

        return false;
    }
}
