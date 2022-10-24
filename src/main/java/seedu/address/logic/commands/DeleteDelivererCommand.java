package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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

        ObservableList<Deliverer> lastShownList = model.getFilteredDelivererList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Deliverer personToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteDeliverer(personToDelete);


        return new CommandResult(String.format(MESSAGE_DELETE_DELIVERER_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDelivererCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDelivererCommand) other).targetIndex)); // state check
    }
}

