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
 * Represents the MarkTripDoneCommand. Extends the Command class.
 */
public class MarkTripDoneCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a trip as done. "
            + "Parameters: "
            + "Index Number (Must be a positive integer) "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_SUCCESS = "Trip marked as done: %1$s";
    public static final String MESSAGE_ALREADY_MARKED_DONE = "This trip already marked as done";

    //private final Title eventToAdd;
    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public MarkTripDoneCommand(Index index) {
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

        Trip tripToMarkDone = list.get(index.getZeroBased());

        if (tripToMarkDone.isDone()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED_DONE);
        }

        tripToMarkDone.markDone();
        model.resetView();
        return new CommandResult(String.format(MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW, tripToMarkDone.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTripDoneCommand // instanceof handles nulls
                && index.equals(((MarkTripDoneCommand) other).index));
    }
}
