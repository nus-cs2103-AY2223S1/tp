package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

/**
 * Represents the DisplayTripCommand
 */
public class DisplayTripCommand extends Command {

    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the trip identified by the index number used in the displayed trips list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_EVENT_SUCCESS = "Display Trip: %1$s \n"
            + "at specified index of displayed trips list";

    private final Index targetIndex;

    public DisplayTripCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Trip> lastShownList = model.getFilteredTripList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
        }

        Trip tripToDisplay = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DISPLAY_EVENT_SUCCESS, tripToDisplay));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisplayTripCommand // instanceof handles nulls
                && targetIndex.equals(((DisplayTripCommand) other).targetIndex)); // state check
    }
}
