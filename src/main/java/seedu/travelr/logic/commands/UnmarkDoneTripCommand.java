package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;

import java.util.List;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

/**
 * Represents the AddEventToTripCommand. Extends the Command class.
 */
public class UnmarkDoneTripCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks done trip. "
            + "Parameters: "
            + "Index Number (Must be a positive integer) "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_SUCCESS = "Trip marked as not done: %1$s";
    public static final String MESSAGE_ALREADY_MARKED_NOT_DONE = "This trip already marked as not done";

    //private final Title eventToAdd;
    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public UnmarkDoneTripCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Trip> list = model.getFilteredTripList();
        if (index.getZeroBased() >= list.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
        }

        Trip tripToUnmark = list.get(index.getZeroBased());

        if (!tripToUnmark.isDone()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED_NOT_DONE);
        }

        tripToUnmark.markNotDone();
        model.resetView();
        return new CommandResult(String.format(MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW, tripToUnmark.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkDoneTripCommand // instanceof handles nulls
                && index.equals(((UnmarkDoneTripCommand) other).index));
    }
}
