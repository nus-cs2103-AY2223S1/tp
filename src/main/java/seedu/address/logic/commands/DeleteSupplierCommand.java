package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a Supplier identified using it's displayed index from the address book.
 */
public class DeleteSupplierCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Supplier identified by index used in the displayed Supplier list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SUPPLIER_SUCCESS = "Deleted Supplier: %1$s";

    public static final String MESSAGE_DELETE_SUPPLIER_FAILURE = "Unable to execute DeleteSupplierCommand.";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}.
     */
    public DeleteSupplierCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Supplier> lastShownList = model.getFilteredSupplierList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Supplier personToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteSupplier(personToDelete);


        return new CommandResult(String.format(MESSAGE_DELETE_SUPPLIER_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSupplierCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSupplierCommand) other).targetIndex)); // state check
    }
}
