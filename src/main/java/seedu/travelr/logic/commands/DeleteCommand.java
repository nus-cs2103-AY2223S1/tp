package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

/**
 * Deletes a trip identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the trip identified by the index number used in the displayed trip list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRIP_SUCCESS = "Deleted Trip: %1$s "
            + "\nEvents in this trip have been returned "
            + "to the bucket list. Current bucket list: ";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Trip> lastShownList = model.getFilteredTripList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
        }

        Trip tripToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTrip(tripToDelete);
        model.updateFilteredEventList(model.getBucketPredicate());
        return new CommandResult(String.format(MESSAGE_DELETE_TRIP_SUCCESS, tripToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
