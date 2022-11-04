package seedu.address.logic.commands.checkcommands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Checks the pets of a supplier.
 */
public class CheckSupplierCommand extends CheckCommand {
    public static final String MESSAGE_SUCCESS = "Checking supplier %1$s";

    /**
     * Constructs a CheckSupplierCommand with index specified.
     */
    public CheckSupplierCommand(Index index) {
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
        if (!(o instanceof Supplier)) {
            throw new CommandException(String.format(Messages.INVALID_SUPPLIER, index.getOneBased()));
        }

        Supplier supplier = (Supplier) o;
        model.checkSupplierPet(supplier);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof CheckSupplierCommand) {
            CheckSupplierCommand other = (CheckSupplierCommand) object;
            return other.index.equals(this.index);
        }

        return false;
    }
}
